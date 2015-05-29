class { '::mysql::server':
  root_password           => 'root',
  override_options => { 'mysqld' => { 'max_connections' => '1024', 'bind-address' => '0.0.0.0' } },
  restart          => true,
}

mysql::db { 'bibliothek':
  user     => 'bibliothek',
  password => '1234',
  host     => '%',
  sql      => '/vagrant/sql/dump.sql',
}