
class { 'dhcp':
#  service_ensure => running,
  dnsdomain      => ['tgm.ac.at'],
  nameservers  => ['10.0.1.20', '10.0.1.21'],
  ntpservers   => ['us.pool.ntp.org'],
  interfaces   => ['eth0'],
#  dnsupdatekey => '/etc/bind/keys.d/rndc.key',
#  dnskeyname   => 'rndc-key',
#  require      => Bind::Key['rndc-key'],
#  pxeserver    => '10.0.1.50',
#  pxefilename  => 'pxelinux.0',
}

dhcp::pool{ 'ops.dc1.example.net':
  network => '10.0.1.0',
  mask    => '255.255.255.0',
  range   => ['10.0.1.100 10.0.1.200'],
  gateway => '10.0.1.1',
}

dhcp::host { 'webserver':
  mac => '00:50:56:00:00:08',
  ip  => '10.0.1.8',
}

dhcp::host { 'dns':
  mac => '00:50:56:00:00:02',
  ip  => '10.0.1.2',
}

dhcp::host { 'gateway':
  mac => '00:50:56:00:00:01',
  ip  => '10.0.1.1',
}

