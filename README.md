# Mutant DNA 💡

a technical challenge

## Prerequisites 📝

Before starting please make sure to have installed:
* JRE 1.8 or higher
* JDK 1.8 or higher
* Maven 3.6.3 or higher

## Build and Run 🔧

```bash
git clone https://github.com/wiccana/mutant-dna.git
cd mutant-dna
mvn clean install
mvn spring-boot:run

```
Application should be served at localhost:8080

## Test running app ✔️

```
curl -v --header "Content-Type: application/json" -d "{\"dna\":[\"ATAG\",\"AAAA\",\"ACGC\",\"AAAC\"]}" http://localhost:8080/mutant
```
should return 200 status code


## Test coverage report with Jacoco 📈

from project directory run:
```
mvn test jacoco:report
```
Report should be accesible from target\site\jacoco\index.html

## Online app on Heroku ☁️

Url: https://mutant-dna.herokuapp.com/
```
curl -v --header "Content-Type: application/json" -d "{\"dna\":[\"ATAG\",\"AAAA\",\"ACGC\",\"AAAC\"]}" https://mutant-dna.herokuapp.com/mutant
```

## Built with ⚡

* [Visual Studio Code] (1.48.2) (https://code.visualstudio.com/)
* [Spring Initializer] (https://start.spring.io/)
* [Spring Boot 2.3.3] (https://spring.io/projects/spring-boot)
* [JDK 1.8] (https://openjdk.java.net/)
* [Maven] (https://maven.apache.org/)
* [JaCoCo] (https://www.jacoco.org/jacoco/trunk/doc/maven.html)

## License 🔖
This project is under GNU General Public License v3.0.
Please check [LICENSE.md](LICENSE.md) for more details.

---
⌨️ by [Brenda Mendez](https://github.com/wiccana)
