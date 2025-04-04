<h1 align="center">🚗 Projeto API de Carros</h1>

<p align="center">
  Um CRUD completo para gerenciamento de veículos com Spring Boot
</p>

<div align="center">
  <img src="https://img.shields.io/badge/Java-17-%23ED8B00?logo=java" alt="Java 17">
  <img src="https://img.shields.io/badge/Spring_Boot-3.1.5-%236DB33F?logo=spring" alt="Spring Boot">
  <img src="https://img.shields.io/badge/H2-Database-%2325A162?logo=h2" alt="H2 Database">
</div>

<h2>📋 Tecnologias Utilizadas</h2>
<ul>
  <li><strong>Java 17</strong> (LTS)</li>
  <li><strong>Spring Boot</strong> (Web, JPA)</li>
  <li><strong>Banco de Dados H2</strong> (em memória para testes)</li>
  <li><strong>Lombok</strong> (para reduzir boilerplate)</li>
  <li><strong>RestAssured</strong> (para testes de API)</li>
  <li><strong>JUnit 5</strong> (para testes unitários e de integração)</li>
</ul>

<h2>🌐 Endpoints da API</h2>

<table>
  <tr>
    <th>Método</th>
    <th>Endpoint</th>
    <th>Descrição</th>
  </tr>
  <tr>
    <td>GET</td>
    <td><code>/api/carros/</code></td>
    <td>Lista todos os carros</td>
  </tr>
  <tr>
    <td>GET</td>
    <td><code>/api/carros/{id}</code></td>
    <td>Busca um carro por ID</td>
  </tr>
  <tr>
    <td>POST</td>
    <td><code>/api/carros</code></td>
    <td>Adiciona um novo carro</td>
  </tr>
  <tr>
    <td>PUT</td>
    <td><code>/api/carros/{id}</code></td>
    <td>Atualiza todos os dados de um carro</td>
  </tr>
  <tr>
    <td>PATCH</td>
    <td><code>/api/carros/{id}</code></td>
    <td>Atualiza parcialmente os dados de um carro</td>
  </tr>
  <tr>
    <td>DELETE</td>
    <td><code>/api/carros/{id}</code></td>
    <td>Remove um carro</td>
  </tr>
  <tr>
    <td>GET</td>
    <td><code>/api/carros/potencia</code></td>
    <td>Top 10 carros mais potentes</td>
  </tr>
  <tr>
    <td>GET</td>
    <td><code>/api/carros/economia</code></td>
    <td>Top 10 carros mais econômicos</td>
  </tr>
  <tr>
    <td>GET</td>
    <td><code>/api/carros/eletricos</code></td>
    <td>Lista todos os carros elétricos</td>
  </tr>
</table>

<h2>▶️ Como Executar o Projeto</h2>

<h3>Pré-requisitos</h3>
<ul>
  <li>JDK 17+</li>
  <li>Maven 3.8+</li>
  <li>IDE (IntelliJ, Eclipse, VSCode, etc.)</li>
</ul>

<h3>Passo a Passo</h3>
<ol>
  <li>Clone o repositório:
    <pre><code>git clone https://github.com/seu-usuario/api-carros.git</code></pre>
  </li>
  <li>Entre na pasta do projeto:
    <pre><code>cd api-carros</code></pre>
  </li>
  <li>Compile e execute:
    <pre><code>mvn spring-boot:run</code></pre>
  </li>
  <li>Acesse a API em:
    <pre><code>http://localhost:8080/api/carros/</code></pre>
  </li>
</ol>

<h2>🧪 Testes Automatizados</h2>
<p>O projeto inclui testes de API usando <strong>RestAssured</strong> para garantir o funcionamento correto dos endpoints.</p>

<h3>Como Rodar os Testes</h3>
<pre><code>mvn test</code></pre>

<h3>Cobertura dos Testes</h3>
<ul>
  <li>Testes de CRUD completo</li>
  <li>Validação de status codes (200, 201, 204, 404)</li>
  <li>Verificação de campos no JSON de resposta</li>
</ul>

<h2>📡 Exemplo de Requisições</h2>

<h3>Criar um Carro (POST)</h3>
<pre><code>curl -X POST http://localhost:8080/api/carros \
  -H "Content-Type: application/json" \
  -d '{
    "marca": "Tesla",
    "modelo": "Model S",
    "ano": 2023,
    "potencia": 670,
    "economia": 5.2,
    "tipo": "ELETRICO",
    "preco": 450000.00
  }'</code></pre>

<h3>Listar Carros Elétricos (GET)</h3>
<pre><code>curl http://localhost:8080/api/carros/eletricos</code></pre>

<h2>📂 Estrutura do Projeto</h2>
<pre>
src/
├── main/
│   ├── java/
│   │   ├── controllers/       # Endpoints da API
│   │   ├── domainmodel/       # Entidades (Carros, TipoCarro)
│   │   ├── repositories/      # JPA Repositories
│   │   ├── service/           # Lógica de negócio
│   │   └── DemoApplication.java
│   └── resources/
│       ├── application.yml    # Configurações do Spring
│       └── data.sql           # Dados iniciais (opcional)
└── test/
    └── java/
        └── controllers/       # Testes de API com RestAssured
</pre>

