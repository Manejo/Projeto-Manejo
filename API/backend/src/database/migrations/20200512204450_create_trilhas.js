
exports.up = function(knex) {
    return knex.schema.createTable('trilhas', function (table) {
        table.string('id').primary();
        table.string('nome').notNullable();
        table.string('dificuldade').notNullable();
        table.integer('capacidade').notNullable();
        table.string('status', 2).notNullable(); //* EX OU AT
        table.string('coordenadas').notNullable();
    });
  };
  
  exports.down = function(knex) {
    return knex.schema.dropTable('trilhas');
  };