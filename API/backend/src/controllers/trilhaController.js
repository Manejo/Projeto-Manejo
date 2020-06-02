const generateUniqueId = require("../utils/generateUniqueId");
const connection = require("../database/connection");

module.exports = {
  async listar(request, response) {
    const trilha = await connection("trilhas").select("*");

    return response.json(trilha);
  },

  async create(request, response) {
    const { nome, dificuldade, capacidade, status, coordenadas } = request.body;

    const id = generateUniqueId();

    await connection("trilhas").insert({
      id,
      nome,
      dificuldade,
      capacidade,
      status,
      coordenadas,
    });

    return response.json({
      id,
    });
  },

  async listarUm(request, response) {
    const { id } = request.params;

    const ocorrencia = await connection("ocorrencias")
      .select("*")
      .where("id", id);

    return response.json(ocorrencia);
  },

  async delete(request, response) {
    const { trilha_id } = request.params;

    const trilha = await connection("trilhas")
      .where("id", trilha_id)
      .select("*")
      .first();

    if (trilha) {
      await connection("trilhas").where("id", trilha_id).delete();

      return response.status(204).send();
    } else {
      return response.status(404).send();
    }
  },

  async alterar(request, response) {
    const { trilha_id } = request.params;
    const { nome, dificuldade, capacidade, status, coordenadas } = request.body;
    const id = trilha_id;

    const trilha = await connection("trilhas")
      .where("id", trilha_id)
      .select("*")
      .first();

    if (trilha) {
      await connection("trilhas").where("id", trilha_id).delete();

      await connection("trilhas").insert({
        id,
        nome,
        dificuldade,
        capacidade,
        status,
        coordenadas,
      });

      const trilha = await connection("trilhas")
        .select("*")
        .where("id", trilha_id);

      return response.json(trilha);
    } else {
      return response.status(404).send();
    }
  },
};
