const express = require('express');

const app = express();
const port = 8080;

// Rotas da API
app.get('/', (req, res) => {
  res.send('API is running!');
});

// Iniciar o servidor
app.listen(port, () => {
  console.log(`Server is running on port ${port}`);
});
