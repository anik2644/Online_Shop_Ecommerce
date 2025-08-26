from typing import Optional
from sqlalchemy.orm import Session

from src.ecommerce.domain.entities.users.role import Role
from src.ecommerce.infrastructure.orm.models import UserORM, UserStatus

class UserRepository:
    def __init__(self, db):
        self.db = db

    def create(self, *, email: str, password_hash: str,
               first_name: str | None, last_name: str | None,
               phone_number: str | None, role: Role) -> UserORM:
        user = UserORM(
            email=email,
            password_hash=password_hash,
            first_name=first_name,
            last_name=last_name,
            phone_number=phone_number,
            role_id=role.id,   # store int
        )

        print(user)
        self.db.add(user)
        self.db.commit()
        self.db.refresh(user)
        return user

    def get_by_email(self, email: str) -> UserORM | None:
        return self.db.query(UserORM).filter(UserORM.email == email).first()