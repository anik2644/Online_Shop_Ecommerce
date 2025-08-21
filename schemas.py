from pydantic import BaseModel
from typing import Optional

class RoleBase(BaseModel):
    role_name: str

class RoleCreate(RoleBase):
    pass

class RoleOut(RoleBase):
    role_id: int
    class Config:
        orm_mode = True

class UserBase(BaseModel):
    first_name: str
    last_name: str
    email: str
    phone_number: Optional[str] = None
    role_id: int

class UserCreate(UserBase):
    password_hash: str

class UserOut(UserBase):
    user_id: int
    status: str
    class Config:
        orm_mode = True
