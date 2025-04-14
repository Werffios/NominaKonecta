# NominaApp: Cálculo de Fecha de Pago de Nómina

Este proyecto implementa un sistema en Java para determinar la próxima fecha de pago de la nómina, teniendo en cuenta que los pagos se realizan el día 15 y 30 de cada mes, y que si la fecha programada no es hábil (por ser fin de semana o un festivo) se debe retroceder al día hábil anterior.

El proyecto utiliza **Java puro** con **JDK 24** y se desarrolla en **IntelliJ IDEA**.

---

## Requisitos

- **IntelliJ IDEA** (Ultimate o Community)
- **JDK 24**  
- **Conexión a Internet**
---

## Estructura del Proyecto

La estructura recomendada del proyecto es la siguiente:

```
NominaApp/ 
└─ src/ 
    ├─ Main.java 
    ├─ PayrollCalculator.java 
    └─ HolidayChecker.java
```

- **Main.java:** Contiene el método `main` donde se solicita la fecha de entrada y se muestra la próxima fecha de pago.
- **PayrollCalculator.java:** Implementa la lógica para determinar la fecha de pago, ajustándola en caso de que la fecha programada no sea hábil.
- **HolidayChecker.java:** Se encarga de consultar el API de festivos para verificar si una fecha es festiva.

---

## Descripción de los Archivos

### Main.java

Este archivo:
- Solicita al usuario una fecha en formato `yyyy-MM-dd`.
- Transforma el string recibido a un objeto `LocalDate`.
- Llama al método `getProximoPago()` de la clase `PayrollCalculator` para obtener la fecha de pago ajustada.
- Imprime la fecha resultante.

### PayrollCalculator.java

Contiene la lógica principal:
- Determina la fecha de pago programada (15 o 30 del mes) basándose en la fecha ingresada.
- Utiliza un bucle para retroceder día a día si la fecha calculada es un día no hábil (por fin de semana o festivo).
- Llama al método `esNoHabil()` para hacer la verificación correspondiente.

### HolidayChecker.java

Este archivo:
- Utiliza la API de Konecta (`https://konecta.calendar.werffios.com/festivo/{fecha}`) para verificar si una fecha es festiva.
- Realiza una petición GET mediante la API HTTP de Java (disponible en JDK 24) y analiza la respuesta de manera simple buscando la clave `"festivo":true`.
- Si hay algún error (por ejemplo, problemas de conexión), asume que la fecha no es festiva.

---

## Instrucciones de Configuración y Ejecución

### Creación del Proyecto en IntelliJ IDEA

1. **Iniciar IntelliJ IDEA:**  
   En la pantalla de bienvenida, selecciona **"New Project"**.

2. **Seleccionar Tipo de Proyecto:**
    - Elige **"Java"** en el panel izquierdo.
    - Configura el SDK seleccionando **JDK 24** y haz clic en **"Next"**.

3. **Configurar el Proyecto:**
    - Asigna el nombre `NominaApp`.
    - Selecciona la ubicación de tu proyecto y haz clic en **"Finish"**.

4. **Crear Archivos Java:**
    - En la carpeta `src`, crea las clases `Main.java`, `PayrollCalculator.java` y `HolidayChecker.java` siguiendo la estructura explicada.

### Ejecución del Programa

- Abre la clase `Main.java` en IntelliJ IDEA.
- Haz clic en el botón **Run** (o utiliza el atajo de teclado) para compilar y ejecutar el programa.
- Ingresa la fecha solicitada en el formato `yyyy-MM-dd`.

---

## Uso del API de Festivos

El proyecto consulta un API alojado en `https://konecta.calendar.werffios.com` para determinar si una fecha es festiva. Por ejemplo:

- **Solicitud:**  
  `https://konecta.calendar.werffios.com/festivo/2025-04-17`

- **Respuesta:**
  ```json
  {
    "fecha": "2025-04-17",
    "festivo": true,
    "nombre_festivo": "Jueves Santo"
  }
  ```

Nota: el API devuelve el nombre del festivo, queda como un extra para el futuro.