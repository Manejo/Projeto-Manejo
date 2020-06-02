const connection = require("../database/connection");

module.exports = {
  async listar(request, response) {
    const { page = 1 } = request.query;

    const [count] = await connection("ocorrencias").count();

    const ocorrencias = await connection("ocorrencias")
      .join("trilhas", "trilhas.id", "=", "ocorrencias.trilha_id")
      .limit(5)
      .offset((page - 1) * 5)
      .select([
        "ocorrencias.*",
        "trilhas.nome",
        "trilhas.dificuldade",
        "trilhas.capacidade",
        "trilhas.status",
        "trilhas.coordenadas",
      ]);

    response.header("X-Total-Count", count["count(*)"]);

    return response.json(ocorrencias);
  },

  async create(request, response) {
    const { titulo, descricao, status } = request.body;

    const trilha_id = request.headers.authorization;

    const [id] = await connection("ocorrencias").insert({
      titulo,
      descricao,
      status,
      trilha_id,
    });

    return response.json({
      id,
    });
  },

  async delete(request, response) {
    const { id } = request.params;
    const trilha_id = request.headers.authorization;

    const ocorrencia = await connection("ocorrencias")
      .where("id", id)
      .select("trilha_id")
      .first();

    if (ocorrencia) {
      if (ocorrencia.trilha_id !== trilha_id) {
        return response.status(401).json({ error: "Operation not permitted." });
      }

      await connection("ocorrencias").where("id", id).delete();

      return response.status(204).send();
    } else {
      return response.status(404).send();
    }
  },

  async listarUm(request, response) {
    const { id } = request.params;

    console.log(id);

    const ocorrencia = await connection("ocorrencias")
      .join("trilhas", "trilhas.id", "=", "ocorrencias.trilha_id")
      .select(["ocorrencias.*", "trilhas.*"])
      .where("id", id);

    return response.json(ocorrencia);
  },

  async alterar(request, response) {
    const { id } = request.params;
    const { titulo, descricao, status } = request.body;
    const trilha_id = request.headers.authorization;

    const ocorrencia = await connection("ocorrencias")
      .where("id", id)
      .select("trilha_id")
      .first();

    if (ocorrencia) {
      await connection("ocorrencias").where("id", id).delete();

      await connection("ocorrencias").insert({
        id,
        titulo,
        descricao,
        status,
        trilha_id,
      });

      const ocorrencia = await connection("ocorrencias")
        .where("id", id)
        .select("*")
        .first();

      return response.json(ocorrencia);
    } else {
      return response.status(404).send();
    }
  },
};
