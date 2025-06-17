from django.shortcuts import render
from django.views.decorators.csrf import csrf_exempt
from rest_framework import status, generics, permissions
import json
from django.http import HttpResponse, JsonResponse
from django.shortcuts import redirect
from rest_framework.response import Response
from rest_framework.views import APIView
from django.utils import timezone
import uuid
from rest_framework.authentication import SessionAuthentication
from rest_framework.permissions import IsAuthenticated, AllowAny
from rest_framework import serializers
# Importaciones para registro usuario
from django.contrib.auth.models import User
from django.contrib.auth import authenticate, login, logout
from rest_framework.decorators import api_view , permission_classes
from .models import Cupon, UsuarioCupon
from .models import CustomUser
from .serializer import CustomUserSerializer
# Fin importaciones registro #
from .models import Roles, Usuario
from .serializer import RolesSerializer
from django.db import transaction
from rest_framework import viewsets
from .models import Producto, CategoriaProducto, Proveedor, Pedido, EstadoPedido, ProductoXPedido, FormaDePago, TipoEnvio, Carrito, Usuario, Cupon, UsuarioCupon, Arrepentimiento
from .serializer import ProductoSerializer, CategoriaProductoSerializer, ProveedorSerializer, PedidoSerializer, EstadoPedidoSerializer, ProductoXPedidoSerializer, FormaDePagoSerializer, TipoEnvioSerializer, UserSerializer, UsuarioSerializer, CarritoSerializer, CuponSerializer, UsuarioCuponSerializer, ArrepentimientoSerializer
from django.views.decorators.csrf import csrf_exempt

from rest_framework_simplejwt.tokens import RefreshToken
from django.contrib.auth import authenticate, login
from rest_framework.response import Response
from rest_framework.views import APIView
from rest_framework import status
from rest_framework.permissions import AllowAny, IsAuthenticated
from rest_framework.decorators import permission_classes, action
from rest_framework_simplejwt.authentication import JWTAuthentication
from rest_framework import generics
from rest_framework_simplejwt.views import TokenObtainPairView , TokenRefreshView
from rest_framework_simplejwt.serializers import TokenObtainPairSerializer
import mercadopago
from django.contrib.auth.hashers import make_password
from django.contrib.auth.hashers import check_password

sdk = mercadopago.SDK("APP_USR-833122140344943-051410-45098cbf690567d10ec9d3bfec64cc08-2437030261")

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

    @action(detail=False, methods=['get'], url_path='por-pedido/(?P<id_pedido>\d+)')
    def productos_por_pedido(self, request, id_pedido=None):
        productos = ProductoXPedido.objects.filter(id_pedido=id_pedido)
        serializer = self.get_serializer(productos, many=True)
        return Response(serializer.data)

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
            print(usuario_serializer.errors)
            print(admin_user_serializer.errors)
            return Response(usuario_serializer.data, status= status.HTTP_201_CREATED)
            #return Response(admin_user_serializer.errors, status= status.HTTP_400_BAD_REQUEST)     

class AddToCartView (APIView):
    def post (self, request):
        carrito_serializer = CarritoSerializer(data = request.data)

        if carrito_serializer.is_valid():
            if Carrito.objects.filter(id_producto=request.data["id_producto"], nombre_usuario=request.data["nombre_usuario"]).exists():
                # Si el carrito ya existe, actualizamos la cantidad
                carrito = Carrito.objects.get(id_producto=request.data["id_producto"], nombre_usuario=request.data["nombre_usuario"])
                carrito.cantidad += request.data["cantidad"]
                carrito.save()
                return Response(carrito_serializer.data, status= status.HTTP_201_CREATED)
            else:
                # Si el carrito no existe, lo creamos
                carrito_serializer.save()
                return Response(carrito_serializer.data, status= status.HTTP_201_CREATED)

        else:
            return Response(carrito_serializer.errors, status= status.HTTP_400_BAD_REQUEST)

class DeleteItemFromCartView (APIView):
    def post (self, request):
        carrito_serializer = CarritoSerializer(data = request.data)

        if carrito_serializer.is_valid():
            if Carrito.objects.filter(id_producto=request.data["id_producto"], nombre_usuario=request.data["nombre_usuario"]).exists():
                # Si el carrito ya existe, actualizamos la cantidad
                carrito = Carrito.objects.get(id_producto=request.data["id_producto"], nombre_usuario=request.data["nombre_usuario"])
                carrito.cantidad -= request.data["cantidad"]
                if (carrito.cantidad <= 0):
                    carrito.delete()
                else:
                    carrito.save()
                return Response(carrito_serializer.data, status= status.HTTP_200_OK)
            else:
                # Si el carrito no existe, lo creamos
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

@api_view(['POST'])
def crear_preferencia(request):
    if request.method == 'POST':
        try:
            data = json.loads(request.body)
            print("Datos recibidos en crear_preferencia:", data)

            items = data.get("items")
            if not items or not isinstance(items, list):
                return JsonResponse({"error": "Lista de items no válida"}, status=400)
            
            external_reference = data.get("external_reference")
            print("External Reference enviada a MP:", external_reference)

            checkout_from = data.get("from")
            from_param = "?from=app" if checkout_from == "app" else ""

            monto_final = data.get("montoFinal", None)
            if monto_final is None:
                return JsonResponse({"error": "Falta el montoFinal"}, status=400)

            preference_data = {
                "items": [
                    {
                        # "title": item["title"],
                        # "quantity": item["quantity"],
                        # "unit_price": float(item["unit_price"]),
                        # "currency_id": "ARS",

                        "title": "Total del pedido con descuento",
                        "quantity": 1,
                        "unit_price": float(monto_final),
                        "currency_id": "ARS",
                    } #for item in items
                ],
                "back_urls": {
                    "success": "https://3077-2803-9800-9883-4725-a5a5-bb72-81ec-583f.ngrok-free.app/api/pago-exitoso" + from_param,
                    "failure": "https://tusitio.com/failure",
                    "pending": "https://tusitio.com/pending"
                },
                "auto_return": "approved",
                "external_reference": external_reference if external_reference else "no-reference"

            }
            print("Preference data enviado a MercadoPago:", preference_data)
            print("External Reference enviada a MP:", external_reference)

            preference_response = sdk.preference().create(preference_data)
            print("Respuesta de Mercado Pago:", preference_response)
            preference = preference_response["response"]

            return JsonResponse({
                "preference_id": preference["id"],
                "init_point": preference["init_point"]
            })
        except KeyError as e:
            return JsonResponse({"error": f"Falta el campo requerido: {str(e)}"}, status=400)
        except Exception as e:
            print("Error al crear preferencia:", str(e))
            return JsonResponse({"error": f"Error al crear preferencia: {str(e)}"}, status=500)

    return JsonResponse({"error": "Método no permitido"}, status=405)


def procesar_pedido(external_reference_completa):
    # Parsear la referencia externa completa
    parts = external_reference_completa.split('|')
    nombre_usuario = parts[0]
    direccion_envio = parts[1] if len(parts) > 1 else ""
    codigo_postal = parts[2] if len(parts) > 2 else ""
    opcion_envio_json = parts[3] if len(parts) > 3 else "{}"
    total_final = float(parts[4]) if len(parts) > 4 else 0.0
    tipo_envio_id = int(parts[5]) if len(parts) > 5 else None
    ciudad_envio = parts[6] if len(parts) > 6 else ""
    descuento = float(parts[7]) if len(parts) > 7 else 0.0

    try:
        opcion_envio = json.loads(opcion_envio_json)
    except:
        opcion_envio = {}
    carrito = Carrito.objects.filter(nombre_usuario=nombre_usuario)

    if not carrito.exists():
        raise Exception("El carrito está vacío o no existe.")

    items_comprados = [
        {
            'id_producto': item.id_producto.id_producto,
            'cantidad': item.cantidad
        } for item in carrito
    ]

    with transaction.atomic():
        for item in items_comprados:
            producto = Producto.objects.get(id_producto=item['id_producto'])
            if producto.stock_actual < item['cantidad']:
                raise Exception(f"Stock insuficiente para el producto {producto.nombre}")
            producto.stock_actual -= item['cantidad']
            producto.save()

        ultimo_pedido = Pedido.objects.all().order_by('-numero_pedido').first()
        numero_pedido = (ultimo_pedido.numero_pedido + 1) if ultimo_pedido else 1

        pedido_data = {
            'nombre_usuario': nombre_usuario,
            'fecha': timezone.now(),
            'id_estado_pedido': 1,
            'numero_pedido': numero_pedido,
            'domicilio_envio': direccion_envio,
            'codigo_postal': codigo_postal,
            'costo_envio': opcion_envio.get('costo', 0),
            'tipo_envio': opcion_envio.get('tipo', ''),
            'datos_envio': json.dumps(opcion_envio),
            'total': total_final,
            'ciudad_envio': ciudad_envio,
            'id_tipo_de_envio': tipo_envio_id,
            'descuento': descuento
        }

        pedido_serializer = PedidoSerializer(data=pedido_data)
        if pedido_serializer.is_valid():
            pedido = pedido_serializer.save()
        else:
            raise Exception(f"Error al crear el pedido: {pedido_serializer.errors}")

        for item in items_comprados:
            producto = Producto.objects.get(id_producto=item['id_producto'])
            ProductoXPedido.objects.create(
                id_producto=producto,
                id_pedido=pedido,
                cantidad=item['cantidad'],
                precio=producto.precio
            )

        carrito.delete()


@api_view(['GET'])
def procesar_pago_exitoso(request):
    print("Procesando el pago...")
    print("Datos recibidos:", request.GET)

    external_reference = request.query_params.get('external_reference') # nombre_usuario
    checkout_from = request.query_params.get('from')

    print("Referencia externa recibida:", external_reference)

    if not external_reference:
        return JsonResponse({'error': 'Referencia externa no recibida'}, status=400)

    try:
        procesar_pedido(external_reference)
        print("Pedido procesado correctamente")
        
        if (checkout_from == "app"):
            response = HttpResponse(status=302)
            response["Location"] = f"petboutique://pago"
            return response
        else:
            return redirect('http://localhost:4200/dashboard')

    except Exception as e:
        print("Error al procesar el pedido:", str(e))
        return JsonResponse({'error': str(e)}, status=500)


# Vistas login / logout #####################################################################################
class Login(TokenObtainPairView):
    serializer_class = CustomTokenObtainPairSerializer
    permission_classes = [AllowAny]

    def post(self, request, *args, **kwargs):
        print("Solicitud de login recibida en el backend")

        username = request.data.get('username')
        password = request.data.get('password')

        print(f"Username: {username}, Password: {password}")

        if not username or not password:
            print("Error: Datos incompletos")
            return Response({"error": "Datos incompletos"}, status=status.HTTP_400_BAD_REQUEST)

        # Django se encarga del hash por dentro
        user = authenticate(username=username, password=password)

        if user is None:
            print("Error: Credenciales incorrectas o usuario no encontrado")
            return Response({"error": "Credenciales incorrectas"}, status=status.HTTP_401_UNAUTHORIZED)

        try:
            usuario = Usuario.objects.get(nombre_usuario=username)
        except Usuario.DoesNotExist:
            print("Error: Usuario no encontrado en tabla Usuario")
            return Response({"error": "Usuario no encontrado"}, status=status.HTTP_404_NOT_FOUND)

        # Token serializer (si todo fue válido)
        login_serializer = self.serializer_class(data=request.data)
        if login_serializer.is_valid():
            print("Login exitoso")
            usuario_serializer = UsuarioSerializer(usuario)
            return Response({
                'token': login_serializer.validated_data.get('access'),
                'refresh_token': login_serializer.validated_data.get('refresh'),
                'usuario': usuario_serializer.data,
                'message': 'Inicio de sesión exitoso'
            }, status=status.HTTP_200_OK)
        else:
            print("Error: Serializer inválido")
            return Response({"error": "Error de autenticación"}, status=status.HTTP_400_BAD_REQUEST) 
             
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
    
class RegisterView(APIView):
    permission_classes = [AllowAny]

    def post(self, request):
        print("Registro recibido en el backend")

        password_plana = request.data.get("password")
        password_encriptada = make_password(password_plana)
        print(f"Contraseña encriptada: {password_encriptada}")

        usuario_serializer = UsuarioSerializer(data=request.data)

        admin_user_data = {
            "first_name": request.data.get("nombre"),
            "last_name": request.data.get("apellido"),
            "username": request.data.get("nombre_usuario"),
            "password": request.data.get("password"),
            "email": request.data.get("email"),
        }

        admin_user_serializer = UserSerializer(data=admin_user_data)

        if usuario_serializer.is_valid() and admin_user_serializer.is_valid():
            usuario_serializer.save()
            admin_user_serializer.save()
            print("Usuario registrado correctamente con contraseña encriptada")
            return Response(usuario_serializer.data, status=status.HTTP_201_CREATED)
        else:
            print("Errores al registrar el usuario:")
            print("Errores usuario_serializer:", usuario_serializer.errors)
            print("Errores admin_user_serializer:", admin_user_serializer.errors)
            return Response({"error": "Error al registrar"}, status=status.HTTP_400_BAD_REQUEST)

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

class CustomTokenRefreshView(TokenRefreshView):
    pass

class UsuarioPorNombreView(APIView):
    permission_clases = [IsAuthenticated]

    def get(self, request, nombre_usuario):
        try:
            usuario = Usuario.objects.get(nombre_usuario=nombre_usuario)
            serializer = UsuarioSerializer(usuario)
            return Response(serializer.data)
        except Usuario.DoesNotExist:
            return Response({"error": "Usuario no encontrado"}, status=404)
    
    def put(self, request, nombre_usuario):
        try:
            usuario = Usuario.objects.get(nombre_usuario=nombre_usuario)
        except Usuario.DoesNotExist:
            return Response({'error': 'Usuario no encontrado'}, status=404)
    
        serializer = UsuarioSerializer(usuario, data=request.data, partial=True)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors, status=400)

@api_view(['GET'])
@permission_classes([IsAuthenticated])
def obtener_user_por_username(request, nombre_usuario):
    try:
        usuario = Usuario.objects.get(nombre_usuario=nombre_usuario)
    except Usuario.DoesNotExist:
        return Response({'error': 'Usuario no encontrado'}, status=404)
    
    if request.method == 'GET':
        serializer = UsuarioSerializer(usuario)
        return Response(serializer.data)
    
    elif request.method == 'PUT':
        serializer = UsuarioSerializer(usuario, data=request.data, partial=True)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors, status=400)
    
    
class CuponViewSet(viewsets.ReadOnlyModelViewSet):
    queryset = Cupon.objects.all()
    serializer_class = CuponSerializer

class UsuarioCuponListCreateView(generics.RetrieveUpdateAPIView):
    serializer_class = UsuarioCuponSerializer
    permission_classes = [permissions.IsAuthenticated]

    def get_object(self):
        return self.request.user
    
@api_view(['GET'])
def obtener_usuario(request, username):
    try:
        usuario = User.objects.get(username=username)
        serializer = UsuarioSerializer(usuario)
        return Response(serializer.data)
    except User.DoesNotExist:
        return Response({"error": "Usuario no encontrado"}, status=404)

class CuponSerializer(serializers.ModelSerializer):
    class Meta:
        model = Cupon
        fields = ['id', 'nombre', 'descripcion', 'tipo_descuento', 'valor_descuento', 'imagen_url', 'fecha_vencimiento']


class MisCuponesAPIView(APIView):
    permission_classes = [IsAuthenticated]

    def get(self, request, nombre_usuario=None):
        if nombre_usuario is None:
            # Si no se pasa el nombre de usuario en la URL, usa el usuario autenticado
            nombre_usuario = request.user.username  

        # Filtra los cupones del usuario
        cupones_usuario = UsuarioCupon.objects.filter(usuario__nombre_usuario=nombre_usuario)
        
        # Obtiene los cupones completos (no solo los IDs)
        cupones = [cupon.cupon for cupon in cupones_usuario]

        # Serializa los cupones completos
        cupones_serializados = CuponSerializer(cupones, many=True)

        return Response(cupones_serializados.data)

    def post(self, request):
        username = request.user.username
        cupon_id = request.data.get('cupon_id')

        try:
            usuario = Usuario.objects.get(nombre_usuario=username)
            cupon = Cupon.objects.get(id=cupon_id)
            # Crear relación si no existe
            usuario_cupon, created = UsuarioCupon.objects.get_or_create(usuario=usuario, cupon=cupon)
            if not created:
                return Response({'mensaje': 'El usuario ya tiene este cupón'}, status=200)
            return Response({'mensaje': 'Cupón agregado correctamente'})
        except Usuario.DoesNotExist:
            return Response({'error': 'Usuario no encontrado'}, status=404)
        except Cupon.DoesNotExist:
            return Response({'error': 'Cupón no encontrado'}, status=404)        

    def delete(self, request, nombre_usuario=None):
        if nombre_usuario is None:
            nombre_usuario = request.user.username

        try:
            usuario = Usuario.objects.get(nombre_usuario=nombre_usuario)
        except Usuario.DoesNotExist:
            return Response({'error': 'Usuario no encontrado'}, status=404)

        relaciones = UsuarioCupon.objects.filter(usuario=usuario)
        cantidad = relaciones.count()
        relaciones.delete()

        return Response({'mensaje': f'Se eliminaron {cantidad} cupon(es) del usuario.'}, status=200)

class ArrepentimientoCreateView(generics.CreateAPIView):
    queryset = Arrepentimiento.objects.all()
    serializer_class = ArrepentimientoSerializer              