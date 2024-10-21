from cryptography.hazmat.primitives.asymmetric import rsa
from cryptography.hazmat.primitives import serialization
import base64

def construct_public_key(n, e):
    # Decodificar n y e del formato base64 URL-safe
    n_int = int.from_bytes(base64.urlsafe_b64decode(n + '=='), 'big')
    e_int = int.from_bytes(base64.urlsafe_b64decode(e + '=='), 'big')

    # Crear la clave pública usando los números RSA
    public_key = rsa.RSAPublicNumbers(e_int, n_int).public_key()

    # Serializar la clave al formato PEM
    return public_key.public_bytes(
        encoding=serialization.Encoding.PEM,
        format=serialization.PublicFormat.SubjectPublicKeyInfo
    )

# Sustituye estos valores con los que obtuviste del JWKS de Auth0
n = "xhTDrbeoLtO1L484cgiC59oOhSTlng4o4xitGhLa7oU2POcxgx61QrEi0scBnlaysxQ1JK21MW3q_m0K0shXbymHK6wfQGYg3lNa8_pfFQO_XEg0xhoCwJ_Fs96iXcQ4hNhrd-4ziWjEzKVDVeqDP0PosxDfWhwoHZL5ixTTCg1wOWZE0be7zo6Bqxw_CwO7NsBjFXRtmNAdTXZ2f30pJwRxmQc1PFipWwMOcHtil9guhL7HgUon0WnBMZrF01wy9_B6sIuOgDN6wrjR5829TE730hpQ9KqQ7mHI_FFEiK_UxzktijwW_db7vrLrN7HXiLIA2Lhbg11ZXtODgBeBXQ"
e = "AQAB"

# Generar la clave pública en formato PEM
public_key_pem = construct_public_key(n, e)

# Imprimir la clave pública en la consola
print(public_key_pem.decode())