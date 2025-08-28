from http.client import HTTPException
from typing import List
# src/ecommerce/controller/products/products_repo.py
from fastapi import APIRouter, Depends
from sqlalchemy.orm import Session

from src.ecommerce.domain.entities.products.products import ProductORM
from src.ecommerce.domain.schemas.products.products import ProductResponse, ProductCreate
from src.ecommerce.service.products.products import ProductService
from src.ecommerce.infrastructure.storage.db import get_db

router = APIRouter(prefix="/products", tags=["products"])

@router.post("/", response_model=ProductResponse, status_code=201)
def create_product(product: ProductCreate, db: Session = Depends(get_db)):
    product_service = ProductService(db)
    created_product = product_service.create_product(product, seller_id=9)  # test user
    return created_product




@router.get("/{product_id}", response_model=ProductResponse)
def get_product(product_id: int, db: Session = Depends(get_db)):
    product_service = ProductService(db)
    product = product_service.get_product_by_id(product_id)
    if product is None:
        raise HTTPException(status_code=404, detail="Product not found")
    return product
#
# @router.get("/", response_model=List[ProductResponse])
# def get_all_products(db: Session = Depends(get_db), svc: ProductService = Depends(ProductService)):
#     db_products = svc.get_all_products()  # This should return a list of ProductResponse
#     return db_products
#
#
# @router.put("/{product_id}", response_model=ProductResponse)
# def update_product(product_id: int, payload: ProductCreate, db: Session = Depends(get_db), svc: ProductService = Depends(ProductService)):
#     return svc.update_product(product_id, payload)
