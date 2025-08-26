from src.ecommerce.domain.entities.users.role import Role
from src.ecommerce.infrastructure.orm.models import UserORM
from src.ecommerce.domain.entities.users.user import User


def to_domain(u: UserORM) -> User:
    return User(
        user_id=u.user_id,
        email=u.email,
        role=Role.get_by_id(u.role_id),   # map back to Role
        first_name=u.first_name,
        last_name=u.last_name,
        phone_number=u.phone_number,
        status=u.status,
    )