# src/ecommerce/controller/users/auth_controller.py
from fastapi import APIRouter, Depends
from src.ecommerce.domain.schemas.users.auth import RegisterRequest, LoginRequest, TokenResponse, MeResponse
from src.ecommerce.controller.users.deps import get_auth_service, get_current_user_email
from src.ecommerce.service.users.auth_service import AuthService

router = APIRouter(prefix="/auth", tags=["auth"])

@router.post("/register", response_model=MeResponse, status_code=200)
def register(payload: RegisterRequest, svc: AuthService = Depends(get_auth_service)):
    user = svc.register(
        email=payload.email,
        password=payload.password,
        first_name=payload.first_name,
        last_name=payload.last_name,
        phone_number=payload.phone_number,
        role_decs=payload.role,        # <-- enum goes in
    )
    return MeResponse(
        user_id=user.user_id,
        email=user.email,
        first_name=user.first_name,
        last_name=user.last_name,
        phone_number=user.phone_number,
        status=user.status,
        role=user.role.description,           # <-- enum comes back
    )

@router.post("/login", response_model=TokenResponse)
def login(payload: LoginRequest, svc: AuthService = Depends(get_auth_service)):
    token = svc.login(email=payload.email, password=payload.password)
    return TokenResponse(access_token=token)

@router.get("/me", response_model=MeResponse)
def me(current_email: str = Depends(get_current_user_email),
       svc: AuthService = Depends(get_auth_service)):
    user = svc.get_user_by_email(current_email)
    return MeResponse(
        user_id=user.user_id,
        email=user.email,
        first_name=user.first_name,
        last_name=user.last_name,
        phone_number=user.phone_number,
        status=user.status,
        role=user.role,           # <-- include role in /me too
    )
