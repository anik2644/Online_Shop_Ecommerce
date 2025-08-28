from sqlalchemy.orm import Session
from src.ecommerce.repository.products.products_repo  import ProductRepository
from src.ecommerce.domain.schemas.products.products  import ProductCreate, ProductResponse
from typing import List

class ProductService:

    def __init__(self, db: Session):
        self.db = db
        self.product_repo = ProductRepository(db)

    def create_product(self, product: ProductCreate, seller_id: int) -> ProductResponse:
        print(product)
        db_product = self.product_repo.create_product(product, seller_id)  # ✅ pass variable
        print(db_product)
        return ProductResponse.model_validate(db_product)  # or .from_orm(db_product) if using Pydantic v1

    def get_product_by_id(self, product_id: int) -> ProductResponse:
        db_product = self.product_repo.get_product_by_id(product_id)
        if db_product:
            return ProductResponse.from_orm(db_product)
        return None

    def get_all_products(self) -> List[ProductResponse]:
        db_products = self.product_repo.get_all_products()  # returns list of Product ORM models
        return [ProductResponse.from_orm(product) for product in db_products]  # convert to Pydantic models

    def update_product(self, product_id: int, product_data: ProductCreate) -> ProductResponse:
        db_product = self.product_repo.update_product(product_id, product_data)
        if db_product:
            return ProductResponse.from_orm(db_product)
        return None
