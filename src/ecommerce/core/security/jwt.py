from datetime import datetime, timedelta, timezone
from jose import jwt, JWTError

from src.ecommerce.core.config.settings import settings

def create_access_token(subject: str) -> str:
    expire = datetime.now(tz=timezone.utc) + timedelta(
        minutes=settings.ACCESS_TOKEN_EXPIRE_MINUTES
    )
    to_encode = {"exp": expire, "sub": subject}
    return jwt.encode(to_encode, settings.SECRET_KEY, algorithm=settings.ALGORITHM)

def decode_token(token: str) -> dict:
    return jwt.decode(token, settings.JWT_SECRET_KEY, algorithms=[settings.JWT_ALGORITHM])

class TokenError(Exception):
    pass

def extract_subject(token: str) -> str:
    try:
        payload = decode_token(token)
        sub = payload.get("sub")
        if not sub:
            raise TokenError("token has no subject")
        return sub
    except JWTError as e:
        raise TokenError(str(e)) from e
