include bind
bind::server::conf { '/etc/named.conf':
  listen_on_addr    => [ 'any' ],
  listen_on_v6_addr => [ 'any' ],
  forwarders        => [ '8.8.8.8', '8.8.4.4' ],
  allow_query       => [ 'localnets' ],
  zones             => {
    'myzone.lan' => [
      'type master',
      'file "myzone.lan"',
    ],
    '1.168.192.in-addr.arpa' => [
      'type master',
      'file "1.168.192.in-addr.arpa"',
    ],
  },
}
