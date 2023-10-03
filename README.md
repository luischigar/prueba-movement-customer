Saludos espero que la estén pasando bien, a continuación, describo en pocas palabras las indicaciones del funcionamiento del sistema: 


En este repositorio vamos a encontrar dos proyectos de spring boot el cuales tienen sus siguientes nombres (movements-account) y (person-customer).

person-customer tiene todos los endpoints que son: cuentas, clientes y movimientos. Pero este proyecto solo gestiona lo que tiene que ver con clientes, lo de cuenta y movimiento solo hace petición al proyecto de (movements-account) este es el que se encarga de esa gestión del dinero y reporte. Como dato adicional deben estar los dos proyectos ejecutando para poder tener un correcto funcionamiento de no ser así mostrara que no puede hacer comunicación a una url definida. Estos proyectos se levantan en diferentes puertos el cual yo lo definí a continuación (9991, 9992).

Para crear un cliente, cuenta, y generar un movimiento se creó tablas para definir el tipo, por ejemplo:

Cliente: la es el género (M) que significa masculino y (F) femenino.
Cuenta es el tipo de cuenta (A) que significa Ahorro y (C) corriente.
Movimiento es el tipo de movimiento (R) que significa retiro y (D) deposito.

Dato adicional en el repositorio podemos encontrar el Json del postman y la base de dato en sql server
