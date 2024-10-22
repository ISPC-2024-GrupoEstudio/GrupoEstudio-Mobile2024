from django.shortcuts import render
from django.views.decorators.csrf import csrf_exempt
from rest_framework import status
from rest_framework.response import Response
from rest_framework.views import APIView
from django.utils import timezone
import uuid
from rest_framework.authentication import SessionAuthentication
from rest_framework.permissions import IsAuthenticated
# Importaciones para registro usuario
from django.contrib.auth.models import User
from django.contrib.auth import authenticate, login, logout
from rest_framework.decorators import api_view
from .models import CustomUser
from .serializer import CustomUserSerializer
# Fin importaciones registro #
from .models import Roles, Usuario
from .serializer import RolesSerializer
from django.db import transaction
from rest_framework import viewsets
from .models import Producto, CategoriaProducto, Proveedor, Pedido, EstadoPedido, ProductoXPedido, FormaDePago, TipoEnvio, Carrito
from .serializer import ProductoSerializer, CategoriaProductoSerializer, ProveedorSerializer, PedidoSerializer, EstadoPedidoSerializer, ProductoXPedidoSerializer, FormaDePagoSerializer, TipoEnvioSerializer, UserSerializer, UsuarioSerializer, CarritoSerializer
from django.views.decorators.csrf import csrf_exempt

from rest_framework_simplejwt.tokens import RefreshToken
from django.contrib.auth import authenticate, login
from rest_framework.response import Response
from rest_framework.views import APIView
from rest_framework import status
from rest_framework.permissions import AllowAny, IsAuthenticated
from rest_framework.decorators import permission_classes

from rest_framework_simplejwt.views import TokenObtainPairView
from rest_framework_simplejwt.serializers import TokenObtainPairSerializer


class CustomTokenObtainPairSerializer(TokenObtainPairSerializer):
    @classmethod
    def get_token(cls, user):
        token = super().get_token(user)
        # Agregar información extra al token si es necesario
        token['nombre_usuario'] = user.username
        return token

class CustomTokenObtainPairView(TokenObtainPairView):
    serializer_class = CustomTokenObtainPairSerializer


# Importaciones API autenticación

# Create your views here.
class ProductoViewSet(viewsets.ModelViewSet):
    queryset = Producto.objects.all()
    serializer_class = ProductoSerializer  

class CategoriaProductoViewSet(viewsets.ModelViewSet):
    queryset = CategoriaProducto.objects.all()
    serializer_class = CategoriaProductoSerializer

class ProveedorViewSet(viewsets.ModelViewSet):
    queryset = Proveedor.objects.all()
    serializer_class = ProveedorSerializer

class PedidoViewSet(viewsets.ModelViewSet):
    queryset = Pedido.objects.all()
    serializer_class = PedidoSerializer

class EstadoPedidoViewSet(viewsets.ModelViewSet):
    queryset = EstadoPedido.objects.all()
    serializer_class = EstadoPedidoSerializer

class ProductosXPerdidoViewSet(viewsets.ModelViewSet):
    queryset = ProductoXPedido.objects.all()
    serializer_class = ProductoXPedidoSerializer

class FormaDePagoViewSet(viewsets.ModelViewSet):
    queryset = FormaDePago.objects.all()
    serializer_class = FormaDePagoSerializer

class TipoEnvioViewSet(viewsets.ModelViewSet):
    queryset = TipoEnvio.objects.all()
    serializer_class = TipoEnvioSerializer

class ProductoViewSet(viewsets.ModelViewSet):
    queryset = Producto.objects.all()
    serializer_class = ProductoSerializer  

class RoleListCreateAPIView(APIView):
    def get(self, request):
        roles = Roles.objects.all()
        serializer = RolesSerializer(roles, many=True)
        return Response(serializer.data)

    def post(self, request):
        serializer = RolesSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

class RoleRetrieveUpdateDestroyAPIView(APIView):
    def get_object(self, pk):
        try:
            return Roles.objects.get(pk=pk)
        except Roles.DoesNotExist:
            raise status.HTTP_404_NOT_FOUND

    def get(self, request, pk):
        role = self.get_object(pk)
        serializer = RolesSerializer(role)
        return Response(serializer.data)

    def put(self, request, pk):
        role = self.get_object(pk)
        serializer = RolesSerializer(role, data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    def delete(self, request, pk):
        role = self.get_object(pk)
        role.delete()
        return Response(status=status.HTTP_204_NO_CONTENT)
    
class ProcessPaymentView(APIView):
    def post(self,resquest, format=None):
        payment_details = resquest.data
        return Response ({"status": "success", "message": "Pago procesado exitosamente"}, status=status.HTTP_200_OK)
    
# Vistas login / logout
    
class RegisterView (APIView):
    permission_classes = [AllowAny]
    def post (self, request):
        nuevo_usuario = request.data
        nuevo_usuario["id_rol"] = 2

        usuario_serializer = UsuarioSerializer(data = nuevo_usuario)
        
        admin_user_data =  {
            "first_name": request.data.get("nombre"),
            "last_name": request.data.get("apellido"),
            "username":  request.data.get("nombre_usuario"),
            "password": request.data.get("password"),
            "email": request.data.get("email"),
        }
        admin_user_serializer = UserSerializer(data = admin_user_data)

        if admin_user_serializer.is_valid() and usuario_serializer.is_valid():
            usuario_serializer.save()
            admin_user_serializer.save()

            return Response(usuario_serializer.data, status= status.HTTP_201_CREATED)
        else:
            return Response(admin_user_serializer.errors, status= status.HTTP_400_BAD_REQUEST)     

class AddToCartView (APIView):
    def post (self, request):
        carrito_serializer = CarritoSerializer(data = request.data)

        if carrito_serializer.is_valid():
            carrito_serializer.save()

            return Response(carrito_serializer.data, status= status.HTTP_201_CREATED)
        else:
            return Response(carrito_serializer.errors, status= status.HTTP_400_BAD_REQUEST)

class DeleteFromCartView (APIView):
    def delete (self, request, id_carrito):
        carrito = Carrito.objects.get(id_carrito=id_carrito)
        carrito.delete()

        return Response(status= status.HTTP_200_OK)
        
class CartView(APIView):
    permission_classes = [IsAuthenticated]
    def get(self, request, nombre_usuario):
        carritos = Carrito.objects.filter(nombre_usuario=nombre_usuario).select_related('id_producto')
        carrito_serializer = CarritoSerializer(carritos, many=True)
       
        producto_ids = [carrito.id_producto.id_producto for carrito in carritos]
        productos = Producto.objects.filter(id_producto__in=producto_ids)
        producto_serializer = ProductoSerializer(productos, many=True)
        
        productos_data = {producto.id_producto: producto_serializer.data[index] for index, producto in enumerate(productos)}
        for carrito_data in carrito_serializer.data:
            id_producto = carrito_data['id_producto']
            carrito_data['producto'] = productos_data.get(id_producto, {})
       
        return Response(carrito_serializer.data, status=status.HTTP_200_OK)

class CheckoutView(APIView):
    def post(self, request):
        items_comprados = request.data.get('items_comprados', [])  # Obtener los productos comprados
        payment_details = request.data.get('payment_details', {})  # Detalles del pago
        nombre_usuario = request.data.get("nombre_usuario")
            
        if not items_comprados or not payment_details or not nombre_usuario:
            return Response({"error": "Datos incompletos en la solicitud"}, status=status.HTTP_400_BAD_REQUEST)

        try:
            usuario = User.objects.get(username=nombre_usuario)
        except User.DoesNotExist:
            return Response({"error": f"Usuario con nombre {nombre_usuario} no encontrado"}, status=status.HTTP_404_NOT_FOUND)

        # Validar detalles de pago
        if not all(key in payment_details for key in ['cardNumber', 'expirationDate', 'cvv']):
            return Response({"error": "Los detalles de pago son incompletos"}, status=status.HTTP_400_BAD_REQUEST)

        process_payment_response = self.process_payment(payment_details)  # Procesar el pago
        if process_payment_response.status_code != status.HTTP_200_OK:
            return process_payment_response  # Si el pago falla, retornar la respuesta de la pasarela de pago

        try:
            with transaction.atomic():
                # Actualizar el stock de los productos comprados
                for item in items_comprados:
                    producto_id = item.get('id_producto')
                    cantidad = item.get('cantidad')
                      # Verificar si el producto existe
                    try:
                        producto = Producto.objects.get(id_producto=producto_id)
                    except Producto.DoesNotExist:
                        return Response({"error": f"Producto con id {producto_id} no encontrado"}, status=status.HTTP_404_NOT_FOUND)
                    
                    # Verificar stock suficiente
                    if producto.stock_actual < cantidad:
                        return Response({"error": f"Stock insuficiente para el producto {producto_id}"}, status=status.HTTP_400_BAD_REQUEST)
                    producto.stock_actual -= cantidad
                    producto.save()

                # obtiene el ultimo numero de pedido y le suma 1
                ultimo_pedido = Pedido.objects.all().order_by('-numero_pedido').first()
                numero_pedido = ultimo_pedido.numero_pedido + 1  

                pedido_data = {
                    'nombre_usuario': nombre_usuario,
                    'fecha': timezone.now(),
                    'id_estado_pedido': 1,  # Puedes establecer un valor predeterminado
                    'numero_pedido': numero_pedido,  # Necesitarás una lógica para generar un número de pedido único
                }

                pedido_serializer = PedidoSerializer(data=pedido_data)
                if pedido_serializer.is_valid():
                    pedido = pedido_serializer.save()
                    for item in items_comprados:
                        producto_id = item.get('id_producto')
                        producto = Producto.objects.get(id_producto=producto_id)
                        ProductoXPedido.objects.create(
                            id_producto=producto,
                            id_pedido=pedido,
                            cantidad=item.get('cantidad'),
                            precio=Producto.objects.get(id_producto=item.get('id_producto')).precio
                        )
                else:
                    return Response({"error": pedido_serializer.errors}, status=status.HTTP_400_BAD_REQUEST)

        except Exception as e:
            return Response({"error": str(e)}, status=status.HTTP_500_INTERNAL_SERVER_ERROR)

        return Response({"message": "Proceso de pago completado y stock actualizado correctamente"}, status=status.HTTP_200_OK)

    def process_payment(self, payment_details):
        card_number = payment_details.get('cardNumber')
        expiration_date = payment_details.get('expirationDate')
        cvv = payment_details.get('cvv')
        if not card_number or not expiration_date or not cvv:
            return Response ({"error": "Detalles de pagos incompletos"}, status=status.HTTP_400_BAD_REQUEST)
        return Response ({"message": "Pago procesado exitosamente"}, status=status.HTTP_200_OK) 
    
# Vistas login / logout #####################################################################################
class Login(TokenObtainPairView):
    serializer_class = CustomTokenObtainPairSerializer
    permission_classes = [AllowAny]

    def post(self, request, *args, **kwargs):
        print("Solicitud recibida en el backend")
        # Recuperamos las credenciales
        username = request.data.get('username', None)
        password = request.data.get('password', None)
        print(f"Username: {username}, Password: {password}")


        # Validar que los campos no estén vacíos
        if username is None or password is None:
            print("Error: Datos incompletos")
            return Response({"error": "Datos incompletos"}, status=status.HTTP_400_BAD_REQUEST)

        # Autenticación
        user = authenticate(username=username, password=password)
        if user:
            # Serializa el token y el usuario
            login_serializer = self.serializer_class(data=request.data)
            if login_serializer.is_valid():
                print(f"Usuario autenticado: {user.username}")
                usuario_serializer = UserSerializer(user)
                return Response({
                    'token': login_serializer.validated_data.get('access'),
                    'refresh_token': login_serializer.validated_data.get('refresh'),
                    'usuario': usuario_serializer.data,
                    'message': 'Inicio de sesión exitoso'
                }, status=status.HTTP_200_OK)

        else:
            print("Error: Credenciales incorrectas")
            return Response({"error": "Credenciales incorrectas"}, status=status.HTTP_404_NOT_FOUND)
        
class LogoutView(APIView):
    def post(self, request):
        try:
            username = request.data.get('username', '')  # Suponiendo que estás usando nombre de usuario
            refresh_token = request.data.get('refresh', '')  # Obtener el token de refresco del cuerpo de la solicitud
            
            usuario = Usuario.objects.filter(nombre_usuario=username).first()  # Buscar el usuario por nombre de usuario

            if usuario is not None:
                # Invalida el token de refresco
                if refresh_token:
                    token = RefreshToken(refresh_token)
                    token.blacklist()  # Invalida el token de refresco

                return Response({'message': 'Sesión cerrada correctamente'}, status=status.HTTP_200_OK)
            else:
                return Response({'error': 'No existe este usuario'}, status=status.HTTP_400_BAD_REQUEST)

        except Exception as e:
            return Response({"error": str(e)}, status=status.HTTP_400_BAD_REQUEST)
    
class RegisterView (APIView):
    def post (self, request):
        usuario_serializer = UsuarioSerializer(data = request.data)
        admin_user_data =  {
            "first_name": request.data.get("nombre"),
            "last_name": request.data.get("apellido"),
            "username":  request.data.get("nombre_usuario"),
            "password": request.data.get("password"),
            "email": request.data.get("email"),
        }
        admin_user_serializer = UserSerializer(data = admin_user_data)

        if usuario_serializer.is_valid() and admin_user_serializer.is_valid():
            usuario_serializer.save()
            admin_user_serializer.save()

            return Response(usuario_serializer.data, status= status.HTTP_201_CREATED)
        else:
    # Imprimir errores para debug
            print(usuario_serializer.errors)
            print(admin_user_serializer.errors)
            return Response({
                "usuario_errors": usuario_serializer.errors,
                "admin_user_errors": admin_user_serializer.errors
            }, status=status.HTTP_400_BAD_REQUEST)

# Para registrar usuarios en BDD
@api_view(['POST'])
def registrar_usuario(request):
    user_data = {
        'username': request.data.get('username'),
        'password': request.data.get('password'),
        'email': request.data.get('email'),
    }
    
    user_serializer = UserSerializer(data=user_data)
    if user_serializer.is_valid():
        user = user_serializer.save()
        
        # Aquí creamos el perfil asociado
        custom_user_data = {
            'user': user.id,
            'first_name': request.data.get('first_name'),
            'last_name': request.data.get('last_name'),
            'username': request.data.get('username'),
            'password': request.data.get('password'),
            'email': request.data.get('email')
        }
        custom_user_serializer = CustomUserSerializer(data=custom_user_data)
        if custom_user_serializer.is_valid():
            custom_user_serializer.save()
            return Response(user_serializer.data, status=status.HTTP_201_CREATED)
        else:
            user.delete()  # Si el perfil no es válido, borramos el usuario creado
            return Response(custom_user_serializer.errors, status=status.HTTP_400_BAD_REQUEST)
    return Response(user_serializer.errors, status=status.HTTP_400_BAD_REQUEST)

class UsuarioViewSet(viewsets.ModelViewSet):
    queryset = Usuario.objects.all()
    serializer_class = UsuarioSerializer 

class CustomTokenObtainPairSerializer(TokenObtainPairSerializer):
    @classmethod
    def get_token(cls, user):
        token = super().get_token(user)
        # Agregar información extra al token si es necesario
        token['nombre_usuario'] = user.username
        return token

class CustomTokenObtainPairView(TokenObtainPairView):
    serializer_class = CustomTokenObtainPairSerializer
        