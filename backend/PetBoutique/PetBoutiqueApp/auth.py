from django.conf import settings
from rest_framework import authentication, exceptions
import jwt

class JWTAuthentication(authentication.BaseAuthentication):
    def authenticate(self, request):
        # Extraer el token del encabezado Authorization
        token = request.headers.get('Authorization')
        if not token:
            return None  # Si no hay token, continuar sin autenticación

        try:
            # Remover el prefijo 'Bearer'
            token = token.split()[1]  
            
            # Decodificar el token usando la clave pública y validar audiencia e issuer
            payload = jwt.decode(
                token,
                settings.PUBLIC_KEY,
                algorithms=['RS256'],
                audience=settings.API_IDENTIFIER,  # Cambia según tu configuración
                issuer=settings.JWT_ISSUER  # Validar el emisor del token
            )
        except jwt.ExpiredSignatureError:
            raise exceptions.AuthenticationFailed('El token ha expirado')
        except jwt.InvalidTokenError:
            raise exceptions.AuthenticationFailed('Token inválido')

        # Extraer el 'sub', que es el ID del usuario en Auth0
        nombre_usuario = payload.get('sub')
        if not nombre_usuario:
            raise exceptions.AuthenticationFailed('Usuario no encontrado')

        # Importar el modelo de usuario
        from django.contrib.auth.models import User  
        try:
            user = User.objects.get(id=nombre_usuario)
        except User.DoesNotExist:
            raise exceptions.AuthenticationFailed('Usuario no encontrado')

        return (user, None)  # Retorna el usuario autenticado
        