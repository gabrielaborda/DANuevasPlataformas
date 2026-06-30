from pydantic import BaseModel


class UserCreate(BaseModel):

    nombre: str
    email: str
    password: str


class UserLogin(BaseModel):

    email: str
    password: str


class ProductResponse(BaseModel):

    id: int
    nombre: str
    descripcion: str
    precio: float
    imagen: str

    class Config:
        from_attributes = True