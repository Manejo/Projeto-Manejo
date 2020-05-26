
exports.up = function(knex) {
    return knex.schema.createTable('ocorrencias', function (table) {
        table.increments();
        table.string('titulo').notNullable();
        table.string('descricao').notNullable();
        table.string('status', 2).notNullable(); //* EX OU AT
        
        
        table.string('trilha_id').notNullable();
        table.foreign('trilha_id').references('id').inTable('trilhas');
    });
};

exports.down = function(knex) {
    return knex.schema.dropTable('ocorrencias');
};
