from sqlalchemy.orm import Session
from src.ecommerce.core.security.password import hash_password, verify_password
from src.ecommerce.core.security.jwt import create_access_token
from src.ecommerce.core.exceptions.http import unauthorized, conflict, not_found
from src.ecommerce.domain.entities.users.role import Role
from src.ecommerce.domain.entities.users.user import User
from src.ecommerce.repository.users.user_repository import UserRepository
from src.ecommerce.service.users.mappers import to_domain

class AuthService:
    def __init__(self, db: Session):
        self.repo = UserRepository(db)

    # registration
    def register(self, *, email, password, first_name, last_name, phone_number, role_decs: str):
        role = Role.get_by_description(role_decs)
        if not role:
            raise ValueError("Invalid role_id")

        user = self.repo.create(
            email=email,
            password_hash=hash_password(password),
            first_name=first_name,
            last_name=last_name,
            phone_number=phone_number,
            role=role,
        )
        return to_domain(user)
    # login
    def login(self, *, email: str, password: str) -> str:
        user = self.repo.get_by_email(email)
        if not user:
            raise unauthorized("Invalid email or password")
        if not verify_password(password, user.password_hash):
            raise unauthorized("Invalid email or password")
        return create_access_token(subject=user.email)

    # load current user by email from token
    def get_user_by_email(self, email: str):
        user = self.repo.get_by_email(email)
        if not user:
            raise not_found("User not found")
        return to_domain(user)

