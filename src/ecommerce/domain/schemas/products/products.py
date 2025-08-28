from pydantic import BaseModel
from decimal import Decimal
from enum import Enum
from datetime import datetime
from typing import Optional
from enum import Enum as PyEnum


class ProductStatus(PyEnum):
    ACTIVE = "ACTIVE"
    INACTIVE = "INACTIVE"
    OUT_OF_STOCK = "OUT_OF_STOCK"




class ProductBase(BaseModel):
    category_id: int
    name: str
    description: Optional[str] = None
    price: Decimal
    discount_price: Optional[Decimal] = None
    stock_quantity: int
    status: ProductStatus = ProductStatus.ACTIVE


class ProductCreate(ProductBase):
    pass


class ProductResponse(BaseModel):
    product_id: int
    seller_id: int
    category_id: int
    name: str
    description: str
    price: Decimal
    discount_price: Decimal | None = None
    stock_quantity: int
    status: str
    created_at: datetime
    updated_at: datetime

    model_config = {
        "from_attributes": True,
        "use_enum_values": True
    }
