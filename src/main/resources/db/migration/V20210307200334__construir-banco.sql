create table login
(
    email  varchar primary key,
    senha  varchar,
    perfil varchar
);

create table endereco
(
    id       integer primary key generated BY DEFAULT AS IDENTITY,
    cep      numeric,
    rua      varchar,
    numero   numeric,
    bairro   varchar,
    complemento varchar,
    cidade varchar,
    estado varchar
);

create table terapeuta
(
    id              integer primary key generated BY DEFAULT AS IDENTITY,
    nome_completo   varchar,
    data_nascimento date,
    crfa            numeric,
    telefone        varchar,
    cpf             varchar,
    especialidade   varchar,
    formacao        varchar,
    id_endereco     integer,
    id_login        varchar,
    foreign key (id_endereco) references endereco (id),
    foreign key (id_login) references login (email)
);

create table paciente
(
    id            integer primary key generated BY DEFAULT AS IDENTITY,
    nome_completo varchar,
    data_nascimento date,
    id_terapeuta  integer,
    id_responsavel integer,
    foreign key (id_terapeuta) references terapeuta (id),
    foreign key (id_responsavel) references responsavel (id)
);

create table responsavel
(
    id            integer primary key generated BY DEFAULT AS IDENTITY,
    nome_completo varchar,
    data_nascimento date,
    telefone      varchar,
    cpf           varchar,
    parentesco    varchar,
    id_endereco   integer,
    id_login      varchar,
    foreign key (id_endereco) references endereco (id),
    foreign key (id_login) references login (email)
);
