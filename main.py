from fastapi import FastAPI, Depends
from sqlalchemy.orm import Session
import models, schemas, crud
from database import engine, Base, get_db

# Create DB tables
Base.metadata.create_all(bind=engine)

app = FastAPI(title="Online Shop Ecommerce API")

@app.get("/")
def root():
    return {"message": "E-commerce API is running 🚀"}

# ---- ROLE Endpoints ----
@app.post("/roles/", response_model=schemas.RoleOut)
def create_role(role: schemas.RoleCreate, db: Session = Depends(get_db)):
    return crud.create_role(db, role)

@app.get("/roles/", response_model=list[schemas.RoleOut])
def list_roles(db: Session = Depends(get_db)):
    return crud.get_roles(db)

# ---- USER Endpoints ----
@app.post("/users/", response_model=schemas.UserOut)
def create_user(user: schemas.UserCreate, db: Session = Depends(get_db)):
    return crud.create_user(db, user)

@app.get("/users/", response_model=list[schemas.UserOut])
def list_users(db: Session = Depends(get_db)):
    return crud.get_users(db)
