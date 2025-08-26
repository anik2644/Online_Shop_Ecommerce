import enum
from typing import Optional, List, Dict

class Role(enum.Enum):
    CUSTOMER = (1, "customer")
    SELLER = (2, "seller")
    ADMIN = (3, "admin")

    def __init__(self, role_id: int, description: str):
        self._id = role_id
        self._description = description

    @property
    def id(self) -> int:
        return self._id

    @property
    def description(self) -> str:
        return self._description

    @classmethod
    def get_by_id(cls, role_id: int) -> Optional["Role"]:
        for role in cls:
            if role.id == role_id:
                return role
        return None

    @classmethod
    def get_by_description(cls, desc: str) -> Optional["Role"]:
        for role in cls:
            if role.description.lower() == desc.lower():
                return role
        return None

    @classmethod
    def get_all(cls) -> List[Dict[str, str | int]]:
        return [{"id": role.id, "name": role.name, "description": role.description} for role in cls]

    def __str__(self):
        return f"{self.name}(id={self.id}, description='{self.description}')"
