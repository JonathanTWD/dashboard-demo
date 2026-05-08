create table users (
    id bigserial primary key,
    name varchar(120) not null,
    email varchar(180) not null unique,
    password_hash varchar(255) not null,
    role varchar(40) not null default 'USER',
    created_at timestamp not null default now(),
    updated_at timestamp not null default now()
);

create table dashboards (
    id bigserial primary key,
    user_id bigint not null,
    name varchar(150) not null,
    description text,
    layout jsonb not null default '{}'::jsonb,
    is_default boolean not null default false,
    created_at timestamp not null default now(),
    updated_at timestamp not null default now(),
    constraint fk_dashboards_user foreign key (user_id) references users (id) on delete cascade
);

create table data_sources (
    id bigserial primary key,
    name varchar(150) not null,
    type varchar(50) not null,
    config jsonb not null,
    is_active boolean not null default true,
    created_at timestamp not null default now(),
    updated_at timestamp not null default now()
);

create table widgets (
    id bigserial primary key,
    dashboard_id bigint not null,
    data_source_id bigint,
    title varchar(150) not null,
    widget_type varchar(50) not null,
    position_x integer not null default 0,
    position_y integer not null default 0,
    width integer not null default 1,
    height integer not null default 1,
    config jsonb not null default '{}'::jsonb,
    refresh_interval_seconds integer not null default 60,
    created_at timestamp not null default now(),
    updated_at timestamp not null default now(),
    constraint fk_widgets_dashboard foreign key (dashboard_id) references dashboards (id) on delete cascade,
    constraint fk_widgets_data_source foreign key (data_source_id) references data_sources (id) on delete set null
);

create table widget_data_points (
    id bigserial primary key,
    widget_id bigint not null,
    label varchar(150),
    value numeric(18,4),
    meta jsonb not null default '{}'::jsonb,
    recorded_at timestamp not null default now(),
    constraint fk_widget_data_points_widget foreign key (widget_id) references widgets (id) on delete cascade
);

create index idx_dashboards_user_id on dashboards (user_id);
create index idx_widgets_dashboard_id on widgets (dashboard_id);
create index idx_widgets_data_source_id on widgets (data_source_id);
create index idx_widget_data_points_widget_id on widget_data_points (widget_id);
create index idx_widget_data_points_recorded_at on widget_data_points (recorded_at);