from pydantic import BaseModel, EmailStr, Field
from src.ecommerce.domain.entities.users.role import Role


class RegisterRequest(BaseModel):
    email: EmailStr
    password: str = Field(min_length=8, max_length=72)
    first_name: str | None = None
    last_name: str | None = None
    phone_number: str | None = None
    role: str = Role.CUSTOMER.description   # default CUSTOMER

class LoginRequest(BaseModel):
    email: EmailStr
    password: str

class TokenResponse(BaseModel):
    access_token: str
    token_type: str = "bearer"

class MeResponse(BaseModel):
    user_id: int
    email: EmailStr
    first_name: str | None
    last_name: str | None
    phone_number: str | None
    status: str
    role: str