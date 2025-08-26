from fastapi import Depends, HTTPException, status
from fastapi.security import OAuth2PasswordBearer
from sqlalchemy.orm import Session

from src.ecommerce.core.security.jwt import extract_subject, TokenError
from src.ecommerce.infrastructure.storage.db import get_db
from src.ecommerce.service.users.auth_service import AuthService

oauth2_scheme = OAuth2PasswordBearer(tokenUrl="/auth/login")  # matches your login route

def get_auth_service(db: Session = Depends(get_db)) -> AuthService:
    return AuthService(db)

def get_current_user_email(token: str = Depends(oauth2_scheme)) -> str:
    try:
        return extract_subject(token)
    except TokenError:
        raise HTTPException(status_code=status.HTTP_401_UNAUTHORIZED, detail="Invalid or expired token")
