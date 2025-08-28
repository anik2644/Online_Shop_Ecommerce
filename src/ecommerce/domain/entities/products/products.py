from sqlalchemy import Column, Integer, String, ForeignKey, DECIMAL, Enum, DateTime, Text
from sqlalchemy.orm import relationship
from sqlalchemy.sql import func
from src.ecommerce.infrastructure.storage.db import Base
from enum import Enum as PyEnum
from sqlalchemy import Enum as SQLEnum

class ProductStatus(PyEnum):
    ACTIVE = "ACTIVE"
    INACTIVE = "INACTIVE"
    OUT_OF_STOCK = "OUT_OF_STOCK"



class ProductORM(Base):
    __tablename__ = 'products'

    product_id = Column(Integer, primary_key=True, autoincrement=True)
    seller_id = Column(
        Integer,
        ForeignKey("users.user_id"),
        nullable=False,
        default=8  # must match an existing user_id in users table
    )
    category_id = Column(
            Integer,
            ForeignKey("categories.category_id"),
            nullable=False,
            default=8  # must match an existing user_id in users table
    )
    name = Column(String(200))
    description = Column(String)
    price = Column(DECIMAL(10, 2))
    discount_price = Column(DECIMAL(10, 2), nullable=True)
    stock_quantity = Column(Integer, default=0)

    status = Column(
        SQLEnum(ProductStatus),
        default=ProductStatus.ACTIVE,
        nullable=False
    )
    created_at = Column(DateTime, server_default=func.now())
    updated_at = Column(DateTime, server_default=func.now(), onupdate=func.now())

    seller = relationship("UserORM", backref="products")
    category = relationship("Category", back_populates="products")

    # Prevent table conflict by allowing redefinition of the table
    __table_args__ = {'extend_existing': True}





class Category(Base):
    __tablename__ = "categories"

    category_id = Column(Integer, primary_key=True, index=True)
    parent_id = Column(Integer, ForeignKey("categories.category_id"), nullable=True)
    name = Column(String(100), nullable=False, unique=True)
    description = Column(Text, nullable=True)

    # self-referencing relationship (for parent/child categories)
    parent = relationship("Category", remote_side=[category_id], backref="subcategories")

    # relationship with products
    products = relationship("ProductORM", back_populates="category")
