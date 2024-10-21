from django.urls import path, include
from rest_framework import routers
from PetBoutiqueApp import views
from .views import RoleListCreateAPIView, RoleRetrieveUpdateDestroyAPIView ,ProcessPaymentView,CheckoutView
from .views import registrar_usuario
from rest_framework_simplejwt.views import TokenObtainPairView, TokenRefreshView
from django.urls import path
from .views import validate_token

router=routers.DefaultRouter()
router.register(r'productos', views.ProductoViewSet)
router.register(r'categorias', views.CategoriaProductoViewSet)
router.register(r'proveedores', views.ProveedorViewSet),
router.register(r'pedidos', views.PedidoViewSet)
router.register(r'productoXPedido', views.ProductosXPerdidoViewSet)
router.register(r'estadoPedido', views.EstadoPedidoViewSet)
router.register(r'formaDePago', views.FormaDePagoViewSet)
router.register(r'tipoEnvio', views.TipoEnvioViewSet)
router.register(r'usuarios', views.UsuarioViewSet)

urlpatterns = [
    path('', include(router.urls)),
    path('roles/', RoleListCreateAPIView.as_view(), name='role-list-create'),
    path('roles/<int:pk>/', RoleRetrieveUpdateDestroyAPIView.as_view(), name='role-retrieve-update-destroy'),
    path('process-payment/', ProcessPaymentView.as_view(), name='process_payment'),

    path('auth/login/',
        views.LoginView.as_view(), name='auth_login'),

    path('auth/logout/',
        views.LogoutView.as_view(), name='auth_logout'),

    path('auth/register/', 
       views. RegisterView.as_view(),name= "auth_register"),

    # Segundo intento de registro de usuarios
    path('register/', registrar_usuario, name='registrar_usuario'),

    path("add-to-cart/", 
         views.AddToCartView.as_view(), name="add_to_cart"),

    path("cart/<str:nombre_usuario>/", 
         views.CartView.as_view(), name="cart"),
        
    path("delete-from-cart/<int:id_carrito>/",
         views.DeleteFromCartView.as_view(), name="cart"),
         
    path('checkout/', CheckoutView.as_view(), name='checkout'),

    path('api/token/', TokenObtainPairView.as_view(), name='token_obtain_pair'),
    path('api/token/refresh/', TokenRefreshView.as_view(), name='token_refresh'),
    path('api/validate_token/', validate_token, name='validate_token'),
]


