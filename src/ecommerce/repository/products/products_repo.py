from sqlalchemy.orm import Session

from src.ecommerce.domain.entities.products.products import ProductORM
from src.ecommerce.domain.schemas.products.products  import ProductCreate
from typing import List, Optional

class ProductRepository:

    def __init__(self, db: Session):
        self.db = db

    def create_product(self, product: ProductCreate, seller_id: int) -> ProductORM:
        db_product = ProductORM(
            seller_id=seller_id,
            category_id=product.category_id,
            name=product.name,
            description=product.description,
            price=product.price,
            discount_price=product.discount_price,
            stock_quantity=product.stock_quantity,
            status=product.status.value.upper()
        )
        self.db.add(db_product)
        self.db.commit()
        self.db.refresh(db_product)
        return db_product



    def get_product_by_id(self, product_id: int) -> Optional[ProductORM]:
        return self.db.query(ProductORM).filter(ProductORM.product_id == product_id).first()

    def get_all_products(self) -> List[ProductORM]:
        return self.db.query(ProductORM).all()

    def update_product(self, product_id: int, product_data: ProductCreate) -> Optional[ProductORM]:
        db_product = self.db.query(ProductORM).filter(ProductORM.product_id == product_id).first()
        if db_product:
            db_product.name = product_data.name
            db_product.description = product_data.description
            db_product.price = product_data.price
            db_product.discount_price = product_data.discount_price
            db_product.stock_quantity = product_data.stock_quantity
            db_product.status = product_data.status
            self.db.commit()
            self.db.refresh(db_product)
            return db_product
        return None
