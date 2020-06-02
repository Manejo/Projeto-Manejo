const express = require('express');
const trilhasController = require('./controllers/trilhaController')
const ocorrenciasController = require('./controllers/ocorrenciaController')
const { celebrate, Segments, Joi } = require('celebrate');

const routes = express.Router();

//! listar trilhas
routes.get('/trilhas', trilhasController.listar);
//! criar trilha
routes.post('/trilhas', celebrate({
    [Segments.BODY]: Joi.object().keys({
        nome: Joi.string().required(),
        dificuldade: Joi.string().required(),
        capacidade: Joi.number().required(),
        status: Joi.string().required().length(2),
        coordenadas: Joi.string().required()
    })
}), trilhasController.create)
//! buscar trilha
routes.get('/trilhas/:trilha_id', celebrate({
    [Segments.PARAMS]: Joi.object().keys({
        trilha_id: Joi.string().required(),
    })
}), trilhasController.listarUm);
//! apagar trilha
routes.delete('/trilhas/:trilha_id', celebrate({
    [Segments.PARAMS]: Joi.object().keys({
        trilha_id: Joi.string().required(),
    }),
    [Segments.HEADERS]: Joi.object({
        authorization: Joi.string().required(),
    }).unknown()
}), trilhasController.delete);
//! alterar trilha
routes.post('/trilhas/:trilha_id', celebrate({
    [Segments.PARAMS]: Joi.object().keys({
        trilha_id: Joi.string().required(),
    })
}), trilhasController.alterar);


//! listar ocorrencias
routes.get('/ocorrencias', celebrate({
    [Segments.QUERY]: Joi.object().keys({
        page: Joi.number(),
    })
}), ocorrenciasController.listar);
//! criar ocorrencias
routes.post('/ocorrencias', ocorrenciasController.create);
//! apagar ocorrencias
routes.delete('/ocorrencias/:id', celebrate({
    [Segments.PARAMS]: Joi.object().keys({
        id: Joi.number().required(),
    }),
    [Segments.HEADERS]: Joi.object({
        authorization: Joi.string().required(),
    }).unknown()
}), ocorrenciasController.delete);
//! listar ocorrencia
routes.get('/ocorrencias/:id', celebrate({
    [Segments.PARAMS]: Joi.object().keys({
        id: Joi.number().required(),
    })
}), ocorrenciasController.listarUm);
//! alterar ocorrencia
routes.post('/ocorrencias/:id', celebrate({
    [Segments.PARAMS]: Joi.object().keys({
        id: Joi.number().required(),
    }),
    [Segments.HEADERS]: Joi.object({
        authorization: Joi.string().required(),
    }).unknown()
}), ocorrenciasController.alterar);

module.exports = routes;