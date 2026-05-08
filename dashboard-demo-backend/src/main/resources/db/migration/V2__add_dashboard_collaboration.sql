create table dashboard_members (
    id bigserial primary key,
    dashboard_id bigint not null,
    user_id bigint not null,
    role varchar(40) not null default 'EDITOR',
    created_at timestamp not null default now(),
    updated_at timestamp not null default now(),
    constraint fk_dashboard_members_dashboard foreign key (dashboard_id) references dashboards (id) on delete cascade,
    constraint fk_dashboard_members_user foreign key (user_id) references users (id) on delete cascade,
    constraint uq_dashboard_members_dashboard_user unique (dashboard_id, user_id)
);

create table dashboard_invites (
    id bigserial primary key,
    dashboard_id bigint not null,
    invited_by_user_id bigint,
    email varchar(180) not null,
    role varchar(40) not null default 'EDITOR',
    status varchar(40) not null default 'PENDING',
    token varchar(120) not null unique,
    expires_at timestamp not null,
    accepted_at timestamp,
    created_at timestamp not null default now(),
    updated_at timestamp not null default now(),
    constraint fk_dashboard_invites_dashboard foreign key (dashboard_id) references dashboards (id) on delete cascade,
    constraint fk_dashboard_invites_invited_by foreign key (invited_by_user_id) references users (id) on delete set null
);

create index idx_dashboard_members_dashboard_id on dashboard_members (dashboard_id);
create index idx_dashboard_members_user_id on dashboard_members (user_id);
create index idx_dashboard_invites_dashboard_id on dashboard_invites (dashboard_id);
create index idx_dashboard_invites_email on dashboard_invites (email);
create index idx_dashboard_invites_status on dashboard_invites (status);