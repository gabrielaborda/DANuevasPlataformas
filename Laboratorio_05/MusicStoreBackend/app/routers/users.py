from fastapi import APIRouter, Depends, HTTPException
from sqlalchemy.orm import Session

from ..database import get_db
from .. import crud, schemas

router = APIRouter(
    prefix="/users",
    tags=["Users"]
)


@router.post("/register")
def register(
    user: schemas.UserCreate,
    db: Session = Depends(get_db)
):

    created = crud.create_user(db, user)

    if created is None:
        raise HTTPException(
            status_code=400,
            detail="El correo ya está registrado"
        )

    return {
        "message": "Usuario registrado correctamente"
    }


@router.post("/login")
def login(
    user: schemas.UserLogin,
    db: Session = Depends(get_db)
):

    logged = crud.login(db, user)

    if logged is None:
        raise HTTPException(
            status_code=401,
            detail="Credenciales incorrectas"
        )

    return {
        "id": logged.id,
        "nombre": logged.nombre,
        "email": logged.email
    }