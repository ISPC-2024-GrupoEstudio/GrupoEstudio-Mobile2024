# This is an auto-generated Django model module.
# You'll have to do the following manually to clean this up:
#   * Rearrange models' order
#   * Make sure each model has one field with primary_key=True
#   * Make sure each ForeignKey and OneToOneField has `on_delete` set to the desired behavior
#   * Remove `managed = False` lines if you wish to allow Django to create, modify, and delete the table
# Feel free to rename the models, but don't rename db_table values or field names.
from django.db import models
# importación para API autenticación
from django.contrib.auth.models import User, AbstractUser

class AuthGroup(models.Model):
    name = models.CharField(unique=True, max_length=150)

    class Meta:
        managed = False
        db_table = 'auth_group'

class AuthGroupPermissions(models.Model):
    id = models.BigAutoField(primary_key=True)
    group = models.ForeignKey(AuthGroup, models.DO_NOTHING)
    permission = models.ForeignKey('AuthPermission', models.DO_NOTHING)

    class Meta:
        managed = False
        db_table = 'auth_group_permissions'
        unique_together = (('group', 'permission'),)

class AuthPermission(models.Model):
    name = models.CharField(max_length=255)
    content_type = models.ForeignKey('DjangoContentType', models.DO_NOTHING)
    codename = models.CharField(max_length=100)

    class Meta:
        managed = False
        db_table = 'auth_permission'
        unique_together = (('content_type', 'codename'),)

class AuthUser(models.Model):
    password = models.CharField(max_length=128)
    last_login = models.DateTimeField(blank=True, null=True)
    is_superuser = models.IntegerField()
    username = models.CharField(unique=True, max_length=150)
    first_name = models.CharField(max_length=150)
    last_name = models.CharField(max_length=150)
    email = models.CharField(max_length=254)
    is_staff = models.IntegerField()
    is_active = models.IntegerField()
    date_joined = models.DateTimeField()

    class Meta:
        managed = False
        db_table = 'auth_user'

class AuthUserGroups(models.Model):
    id = models.BigAutoField(primary_key=True)
    user = models.ForeignKey(AuthUser, models.DO_NOTHING)
    group = models.ForeignKey(AuthGroup, models.DO_NOTHING)

    class Meta:
        managed = False
        db_table = 'auth_user_groups'
        unique_together = (('user', 'group'),)

class AuthUserUserPermissions(models.Model):
    id = models.BigAutoField(primary_key=True)
    user = models.ForeignKey(AuthUser, models.DO_NOTHING)
    permission = models.ForeignKey(AuthPermission, models.DO_NOTHING)

    class Meta:
        managed = False
        db_table = 'auth_user_user_permissions'
        unique_together = (('user', 'permission'),)

class Carrito(models.Model):
    id_carrito = models.BigAutoField(primary_key=True)
    id_producto = models.ForeignKey('Producto', models.DO_NOTHING, db_column='id_producto')
    nombre_usuario = models.ForeignKey('Usuario', models.DO_NOTHING, db_column='nombre_usuario')
    cantidad = models.IntegerField(blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'carrito'

class CategoriaProducto(models.Model):
    id_categoria_producto = models.AutoField(primary_key=True)
    nombre = models.CharField(max_length=45, blank=True, null=True)
    descripcion = models.CharField(max_length=45, blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'categoria_producto'

    def __str__(self) -> str:
        return "{}".format(self.nombre)

class DjangoAdminLog(models.Model):
    action_time = models.DateTimeField()
    object_id = models.TextField(blank=True, null=True)
    object_repr = models.CharField(max_length=200)
    action_flag = models.PositiveSmallIntegerField()
    change_message = models.TextField()
    content_type = models.ForeignKey('DjangoContentType', models.DO_NOTHING, blank=True, null=True)
    user = models.ForeignKey(AuthUser, models.DO_NOTHING)

    class Meta:
        managed = False
        db_table = 'django_admin_log'

class DjangoContentType(models.Model):
    app_label = models.CharField(max_length=100)
    model = models.CharField(max_length=100)

    class Meta:
        managed = False
        db_table = 'django_content_type'
        unique_together = (('app_label', 'model'),)

class DjangoMigrations(models.Model):
    id = models.BigAutoField(primary_key=True)
    app = models.CharField(max_length=255)
    name = models.CharField(max_length=255)
    applied = models.DateTimeField()

    class Meta:
        managed = False
        db_table = 'django_migrations'

class DjangoSession(models.Model):
    session_key = models.CharField(primary_key=True, max_length=40)
    session_data = models.TextField()
    expire_date = models.DateTimeField()

    class Meta:
        managed = False
        db_table = 'django_session'

class EstadoPedido(models.Model):
    id_estado_pedido = models.IntegerField(primary_key=True)
    nombre = models.CharField(max_length=45, blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'estado_pedido'

    def __str__(self):
        return str(self.id_estado_pedido)

class FormaDePago(models.Model):
    id_forma_de_pago = models.IntegerField(primary_key=True)
    desc = models.CharField(max_length=45, blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'forma_de_pago'

    def __str__(self):
        return str(self.id_forma_de_pago)

class Pedido(models.Model):
    id_pedido = models.AutoField(primary_key=True)
    fecha = models.DateTimeField(blank=True, null=True)
    id_estado_pedido = models.ForeignKey(EstadoPedido, models.DO_NOTHING, db_column='id_estado_pedido', blank=True, null=True)
    nombre_usuario = models.ForeignKey('Usuario', models.DO_NOTHING, db_column='nombre_usuario', blank=True, null=True)
    id_tipo_de_envio = models.ForeignKey('TipoEnvio', models.DO_NOTHING, db_column='id_tipo_de_envio', blank=True, null=True)
    domicilio_envio = models.CharField(max_length=150, blank=True, null=True)
    id_forma_de_pago = models.ForeignKey(FormaDePago, models.DO_NOTHING, db_column='id_forma_de_pago', blank=True, null=True)
    numero_pedido = models.IntegerField(unique=True)
    total = models.DecimalField(max_digits=10, decimal_places=2, default=0)
    codigo_postal = models.CharField(max_length=10, blank=True, null=True)
    costo_envio = models.DecimalField(max_digits=10, decimal_places=2, blank=True, null=True)
    ciudad_envio = models.CharField(max_length=100, blank=True, null=True)
    descuento = models.DecimalField(max_digits=10, decimal_places=2, blank=True, null=True, default=0)
    localidad = models.CharField(max_length=100, blank=True, null=True)


    class Meta:
        managed = False
        db_table = 'pedido'

        
    def __str__(self):
        return str(self.id_pedido)

class Producto(models.Model):
    id_producto = models.AutoField(primary_key=True)
    nombre = models.CharField(max_length=255, blank=True, null=True)
    descripcion = models.TextField(blank=True, null=True)
    precio = models.FloatField(blank=True, null=True)
    stock_actual = models.IntegerField(blank=True, null=True)
    id_proveedor = models.ForeignKey('Proveedor', models.DO_NOTHING, db_column='id_proveedor', blank=True, null=True)
    stock_minimo = models.IntegerField(blank=True, null=True)
    id_categoria_producto = models.ForeignKey(CategoriaProducto, models.DO_NOTHING, db_column='id_categoria_producto', blank=True, null=True)
    image_url = models.TextField(blank=True, null=True)
    peso = models.DecimalField(max_digits=5, decimal_places=2, default=0.50)

    class Meta:
        managed = False
        db_table = 'producto'
    
    def __str__(self):
        return self.nombre

class ProductoXPedido(models.Model):
    id = models.BigAutoField(primary_key=True)
    id_producto = models.ForeignKey(Producto, models.DO_NOTHING, db_column='id_producto', blank=True, null=True)
    id_pedido = models.ForeignKey(Pedido, models.DO_NOTHING, db_column='id_pedido', blank=True, null=True)
    cantidad = models.IntegerField(blank=True, null=True)
    precio = models.FloatField(blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'producto_x_pedido'

    def __str__(self):
        return self.id


class ProductoXVenta(models.Model):
    id = models.BigAutoField(primary_key=True)
    id_producto = models.ForeignKey(Producto, models.DO_NOTHING, db_column='id_producto', blank=True, null=True)
    id_venta = models.ForeignKey('Venta', models.DO_NOTHING, db_column='id_venta', blank=True, null=True)
    cantidad = models.IntegerField(blank=True, null=True)
    precio_unitario = models.FloatField(blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'producto_x_venta'

    def __str__(self):
        return self.id

class Proveedor(models.Model):
    id_proveedor = models.AutoField(primary_key=True)
    nombre = models.CharField(max_length=45, blank=True, null=True)
    direccion = models.CharField(max_length=45, blank=True, null=True)
    telefono = models.CharField(max_length=45, blank=True, null=True)
    mail = models.CharField(max_length=45, blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'proveedor'

    def __str__(self) -> str:
        return "{}".format(self.nombre)

class Rol(models.Model):
    id_rol = models.AutoField(primary_key=True)
    nombre_del_rol = models.CharField(max_length=45, blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'rol'

    def __str__(self):
        return self.nombre_del_rol

class TipoDocumento(models.Model):
    id_tipo_documento = models.AutoField(primary_key=True)
    nombre = models.CharField(max_length=45, blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'tipo_documento'

    def __str__(self):
        return self.nombre

class TipoEnvio(models.Model):
    id_tipo_envio = models.IntegerField(primary_key=True)
    desc = models.CharField(max_length=45, blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'tipo_envio'

    def __str__(self):
        return str(self.id_tipo_envio)
    
class Cupon(models.Model):
    TIPO_DESCUENTO_CHOICES = [
        ('PORCENTAJE', 'Porcentaje'),
        ('MONTO', 'Monto fijo'),
    ]

    nombre = models.CharField(max_length=50)
    descripcion = models.TextField()
    tipo_descuento = models.CharField(max_length=20, choices=TIPO_DESCUENTO_CHOICES)
    valor_descuento = models.FloatField()
    imagen_url = models.URLField(blank=True, null=True)
    fecha_vencimiento = models.DateField()
    image_url = models.TextField(blank=True, null=True)    

    class Meta:
        verbose_name = "Cupón"
        verbose_name_plural = "Cupones"

    def __str__(self):
        return f"{self.nombre} ({self.tipo_descuento})"
    




# Incorporamos usuario a registrar
class Usuario(models.Model):
    nombre_usuario = models.CharField(primary_key=True, max_length=12)
    nombre = models.CharField(max_length=45, blank=True, null=True)
    direccion = models.CharField(max_length=45, blank=True, null=True)
    telefono = models.IntegerField(blank=True, null=True)
    email = models.CharField(max_length=45, blank=True, null=True)
    apellido = models.CharField(max_length=45, blank=True, null=True)
    id_tipo_documento = models.ForeignKey(TipoDocumento, models.DO_NOTHING, db_column='id_tipo_documento', blank=True, null=True)
    numero_documento = models.IntegerField(blank=True, null=True)
    numero_cliente = models.IntegerField(blank=True, null=True)
    id_rol = models.ForeignKey(Rol, models.DO_NOTHING, db_column='id_rol', blank=True, null=True)
    estado = models.TextField(blank=True, null=True)  # This field type is a guess.
    # password = models.CharField(max_length=45, blank=True, null=True)
    fotoPerfil = models.CharField(max_length=600, blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'usuario'

    def __str__(self):
        return self.nombre_usuario

class UsuarioCupon(models.Model):
    #usuario = models.ForeignKey(User, on_delete=models.CASCADE, related_name="cupones_usuario")
    usuario = models.ForeignKey(Usuario, on_delete=models.CASCADE, related_name="cupones_usuario")
    cupon = models.ForeignKey(Cupon, on_delete=models.CASCADE)
    usado = models.BooleanField(default=False)
    fecha_aplicado = models.DateTimeField(null=True, blank=True)

    class Meta:
        unique_together = ('usuario', 'cupon')

class Venta(models.Model):
    id_venta = models.AutoField(primary_key=True)
    fecha = models.CharField(max_length=45, blank=True, null=True)
    nombre_usuario = models.ForeignKey(Usuario, models.DO_NOTHING, db_column='nombre_usuario', blank=True, null=True)
    id_pedido = models.ForeignKey(Pedido, models.DO_NOTHING, db_column='id_pedido', blank=True, null=True)
    monto = models.FloatField(blank=True, null=True)
    numero_factura = models.CharField(max_length=100, blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'venta'

class Roles(models.Model):
    nombre = models.CharField(max_length=100, unique=True)
    descripcion = models.TextField()
    permisos = models.ManyToManyField(AuthPermission, blank=True)
    activo = models.BooleanField(default=True)
    fecha_creacion = models.DateTimeField(auto_now_add=True)
    fecha_modificacion = models.DateTimeField(auto_now=True)

    def __str__(self):
        return self.nombre
    
# Custom User para la creación de nuevos usuarios
class CustomUser(models.Model):
    user = models.OneToOneField(User, on_delete=models.CASCADE)
    first_name = models.CharField(max_length=30)
    last_name = models.CharField(max_length=30)
    username = models.CharField(max_length=150, unique=True)
    password = models.CharField(max_length=128)
    email = models.EmailField(unique=True)

    def __str__(self):
        return self.username
 
class Arrepentimiento(models.Model):
    nombre = models.CharField(max_length=100)
    telefono = models.CharField(max_length=20)
    email = models.EmailField()
    motivo = models.TextField(blank=True, null=True)
    fecha_envio = models.DateTimeField(auto_now_add=True)

    def __str__(self):
        return f'{self.nombre} - {self.email}'