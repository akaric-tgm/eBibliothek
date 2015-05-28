class { '::mysql::server':
  root_password           => 'root',
  remove_default_accounts => true,
  override_options        => $override_options
}
