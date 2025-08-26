from dataclasses import dataclass
from typing import Optional, Literal

from src.ecommerce.domain.entities.users.role import Role

Status = Literal["active", "inactive", "banned"]

@dataclass(frozen=True)
class User:
    user_id: int
    email: str
    role: Role
    first_name: Optional[str]
    last_name: Optional[str]
    phone_number: Optional[str]
    status: str
