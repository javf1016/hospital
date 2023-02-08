# hospital
Prueba Makaia


POST
* Endpoint: http://localhost:8000/agendar
* JSON: {
    "specialty": "Medicina G",
    "userId": "1234567890",
    "userType": "POLIZA"
}
* Respuesta: {
    "id": 2,
    "dateAppointment": "2023-02-22"
}
* Respuesta : {
    "mensaje": "El usuario con identificación 1234567890 ya tiene una cita agendada, por lo cual no podrá realizar más agendamientos."
}

GET 
* Endpoint: http://localhost:8000/agendar/1

* Respuesta: {
    "id": 1,
    "specialty": "Medicina G",
    "userId": "1234567890",
    "userType": "POLIZA",
    "dateAppointment": "2023-02-16"
}
