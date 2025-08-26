from sqlalchemy import Column, Integer, String, Enum, DateTime, func, ForeignKey
from sqlalchemy.orm import relationship
import enum

from src.ecommerce.domain.entities.users.role import Role
from src.ecommerce.infrastructure.storage.db import Base


class UserStatus(str, enum.Enum):
    active = "active"
    inactive = "inactive"
    banned = "banned"




class UserORM(Base):
    __tablename__ = "users"

    user_id       = Column(Integer, primary_key=True, autoincrement=True)
    role_id       = Column(Integer, nullable=False)  # stores Role.id
    first_name    = Column(String(100), nullable=True)
    last_name     = Column(String(100), nullable=True)
    email         = Column(String(150), unique=True, index=True, nullable=False)
    password_hash = Column(String(255), nullable=False)
    phone_number  = Column(String(50), nullable=True)
    status        = Column(String(20), nullable=False, default="active")
    created_at    = Column(DateTime, server_default=func.current_timestamp(), nullable=False)
    updated_at    = Column(DateTime, server_default=func.current_timestamp(),
                           onupdate=func.current_timestamp(), nullable=False)