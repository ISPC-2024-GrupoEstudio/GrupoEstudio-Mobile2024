from rest_framework import serializers
from .models import Producto, CategoriaProducto, Proveedor, Pedido, EstadoPedido, ProductoXPedido, Roles, FormaDePago, TipoEnvio, Carrito, Usuario, Cupon, Arrepentimiento
# Importaciones referentes a Custom User
from .models import CustomUser
from django.contrib.auth.models import User

# serializador creación usuarios
from django.contrib.auth.models import User

class ProductoSerializer(serializers.ModelSerializer):
    class Meta:
        model = Producto
        fields = '__all__'  

class UsuarioSerializer(serializers.ModelSerializer):
    telefono = serializers.IntegerField(max_value=9223372036854775807, required=False, allow_null=True)

    class Meta:
        model = Usuario
        fields = '__all__'  

class CategoriaProductoSerializer(serializers.ModelSerializer):
    class Meta:
        model = CategoriaProducto
        fields = '__all__'  

class ProveedorSerializer(serializers.ModelSerializer):
    class Meta:
        model = Proveedor
        fields = '__all__'

class PedidoSerializer(serializers.ModelSerializer):
    class Meta:
        model = Pedido
        fields = '__all__'

class EstadoPedidoSerializer(serializers.ModelSerializer):
    class Meta:
        model = EstadoPedido
        fields = '__all__'

class ProductoXPedidoSerializer(serializers.ModelSerializer):
    class Meta:
        model = ProductoXPedido
        fields = '__all__'

class RolesSerializer(serializers.ModelSerializer):
    class Meta:
        model = Roles
        fields = '_all_'

class FormaDePagoSerializer(serializers.ModelSerializer):
    class Meta:
        model = FormaDePago
        fields = '__all__'

class TipoEnvioSerializer(serializers.ModelSerializer):
    class Meta:
        model = TipoEnvio
        fields = '__all__'

# conversión json creación usuarios
class UserSerializer(serializers.Serializer):
    id = serializers.ReadOnlyField()
    first_name = serializers.CharField()
    last_name = serializers.CharField()
    username = serializers.CharField()
    email = serializers.EmailField()
    password = serializers.CharField()

    def create(self, validate_data):
        instance = User()
        instance.first_name = validate_data.get('first_name')
        instance.last_name = validate_data.get('last_name')
        instance.username = validate_data.get('username')
        instance.email = validate_data.get('email')
        instance.set_password (validate_data.get('password'))
        instance.save()
        return instance

# validación usuarios registrados  
    def validate_username(self, data):
        users = User.objects.filter(username = data)
        if len(users) != 0:
            raise serializers.ValidationError("Este nombre de usuario ya existe, ingrese uno nuevo.")
        else:
            return data
        
class CarritoSerializer(serializers.ModelSerializer):
    class Meta:
        model = Carrito
        fields = '__all__'

# Custom User serializer / sin uso en el servicio
class CustomUserSerializer(serializers.ModelSerializer):
    class Meta:
        model = CustomUser
        fields = ['first_name', 'last_name', 'username', 'password', 'email', 'user']
        extra_kwargs = {'password': {'write_only': True}}

    def create(self, validated_data):
        user_data = validated_data.pop('user')
        user = User.objects.create_user(**user_data)
        custom_user = CustomUser.objects.create(user=user, **validated_data)
        return custom_user
        

class CuponSerializer(serializers.ModelSerializer):
    class Meta:
        model = Cupon
        fields = '__all__'

class UsuarioCuponSerializer(serializers.ModelSerializer):
    cupones = serializers.PrimaryKeyRelatedField(queryset=Cupon.objects.all(), many=True)

    class Meta:
        model = Usuario
        fields = ['cupones']


class ArrepentimientoSerializer(serializers.ModelSerializer):
    class Meta:
        model = Arrepentimiento
        fields = '__all__'