# Prototipo 2 – App de Hábitos

Aplicación Android desarrollada para la prueba Prototipo 2 de Programación Android (IPST). Permite crear y gestionar hábitos personales, usando intents explícitos e implícitos para conectar pantallas y funcionalidades del sistema.

## Funcionalidades

- Crear hábitos nuevos (nombre, descripción, teléfono de contacto)
- Ver detalle de cada hábito con su racha de días
- Editar hábitos existentes
- Compartir progreso por cualquier app instalada
- Ver tips de hábitos en el navegador
- Poner recordatorio diario
- Tomar foto como evidencia
- Llamar a un compañero de apoyo

## Intents implementados (8 total)

### Explícitos (3)
| Intent | Descripción |
|---|---|
| MainActivity → AddHabitActivity | Abre el formulario para crear un hábito |
| MainActivity → HabitDetailActivity | Abre el detalle de un hábito seleccionado |
| HabitDetailActivity → EditHabitActivity | Abre el formulario de edición |

### Implícitos (5)
| Intent | Acción del sistema |
|---|---|
| Compartir progreso | `ACTION_SEND` |
| Ver tips de hábitos | `ACTION_VIEW` (navegador) |
| Poner recordatorio | `AlarmClock.ACTION_SET_ALARM` |
| Tomar foto de evidencia | `MediaStore.ACTION_IMAGE_CAPTURE` |
| Llamar a compañero | `ACTION_DIAL` |

## Capturas de pantalla

| Lista de hábitos | Detalle del hábito | Agregar hábito |
|---|---|---|
| ![Lista](screenshots/lista.png) | ![Detalle](screenshots/detalle.png) | ![Agregar](screenshots/agregar.png) |

## Tecnologías

- Java
- Android Studio
- XML (ConstraintLayout / LinearLayout)

## Autor

Juan Pablo (StaticDreamx64) — Ingeniería en Informática, IPST