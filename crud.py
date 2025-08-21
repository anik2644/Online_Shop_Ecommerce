from sqlalchemy.orm import Session
import models, schemas

# ---- ROLE CRUD ----
def create_role(db: Session, role: schemas.RoleCreate):
    db_role = models.Role(role_name=role.role_name)
    db.add(db_role)
    db.commit()
    db.refresh(db_role)
    return db_role

def get_roles(db: Session):
    return db.query(models.Role).all()

# ---- USER CRUD ----
def create_user(db: Session, user: schemas.UserCreate):
    db_user = models.User(**user.dict())
    db.add(db_user)
    db.commit()
    db.refresh(db_user)
    return db_user

def get_users(db: Session):
    return db.query(models.User).all()
