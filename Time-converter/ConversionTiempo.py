'''
Created on 9 jul. 2019

@author: Setito
'''

import datetime as dt
import numpy as np
import time

Fechas = []

# Permite introducir los datos por teclado
def IntroducirFecha():
    dia = int(input('Introduce el dia '))
    mes = int(input('Introduce el mes '))
    anio = int(input('Introduce el anio '))
    intervaloTemporal = int(input('Introduce el numero de dias '))
    fecha = dt.date(anio,mes,dia)
    inicio = time.mktime(fecha.timetuple())
    ObtenerFechasUnix(inicio, intervaloTemporal)

# Permite pasar los datos por parametro desde otro modulo
def PasarFecha(anio, mes, dia, intervaloTemporal):
    fecha = dt.date(anio,mes,dia)
    tiempoUnix = time.mktime(fecha.timetuple())
    ObtenerFechasUnix(tiempoUnix, intervaloTemporal)

# Obtiene la lista en formato tiempo Unix
def ObtenerFechasUnix(inicio, intervaloTemporal):
    FechasUnix = []
    FechasUnix.append(inicio)
    for i in list(range(1, intervaloTemporal)):
        inicio = inicio+86400 # Suma un dia cada vez
        FechasUnix.append(tiempo)
    ObtenerFechas(FechasUnix)

# Obtiene la lista en formato tiempo normal
def ObtenerFechas(FechasUnix):
    dateconv = np.vectorize(dt.datetime.fromtimestamp)
    for tiempo in FechasUnix:
        tiempo = str(dateconv(tiempo))[:11] # Recorta para que solo se muestre la fecha
        Fechas.append(tiempo)

print(Fechas)

