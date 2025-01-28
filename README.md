# 🌐 *Acortador de URLs*

Un servicio de acortador de URL crea una URL corta/alias/URL pequeña a partir de una URL larga. Además, cuando el usuario hace clic en la URL pequeña, es redirigido a la URL original.

Las URL pequeñas son extremadamente útiles para compartir a través de SMS/tweets (donde hay un límite en el número de caracteres que se pueden enviar/twittear) y también cuando se imprimen en libros/revistas, etc. (menos caracteres implica un menor costo de impresión). Además, es más fácil y menos propenso a errores escribir una URL corta en comparación con su versión más larga.

![Ilustración](https://www.codesmith.io/hs-fs/hubfs/Blog%20Images/Blog%20Photos/fig-6-redirection.png?width=1308&height=1212&name=fig-6-redirection.png)

## 📚 *Sobre el proyecto*

Este proyecto tiene el objetivo de servir como práctica para el aprendizaje de la infraestructura de un backend.

A pesar de ser un proyecto sencillo, se aprovechará para utilizar tecnologías que permitan la escalabilidad, rendimiento, seguridad y mantenimiento del sistema.

**Artículos de referencia**:
- [Diagramming System Design: URL Shorteners](https://www.codesmith.io/blog/diagramming-system-design-url-shorteners) by Iván Ovejero
- [System Design : Scalable URL shortener service like TinyURL](https://medium.com/@sandeep4.verma/system-design-scalable-url-shortener-service-like-tinyurl-106f30f23a82) by Sandeep Verma

## 📋 *Requerimientos*

### 🔗 *Gestionar URLs*

- *Añadir URL*: El usuario podrá añadir una URL larga y obtener una URL corta.
- *Eliminar URL*: El usuario podrá eliminar una URL corta.
- *Redirigir URL*: Cuando un usuario visite una URL corta, será redirigido a la URL original.
- *QR Code*: El usuario podrá obtener un código QR de una URL corta.

### 📊 *Monitorización*

- *Obtener estadísticas*: El usuario podrá obtener estadísticas de una URL corta, como el número de visitas.

### 🔒 *Seguridad*

- *Autenticación*: El usuario deberá autenticarse para poder gestionar URLs.
- *Autorización*: El usuario solo podrá gestionar URLs que haya creado.
- *Integración con otros servicios: Se deberá integrar un servicio de autenticación de terceros, como *Google o GitHub.

