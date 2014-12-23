# -*- mode: ruby -*-
# vi: set ft=ruby :

# Vagrantfile API/syntax version. Don't touch unless you know what you're doing!
VAGRANTFILE_API_VERSION = "2"

Vagrant.configure(VAGRANTFILE_API_VERSION) do |config|
  config.vm.box = "auto_sample_app"
  config.vm.box_url = "https://github.com/G-F/automation-sample/releases/download/ver0.1/centos_sampleapp.box"
  config.vm.network :forwarded_port, host: 4567, guest: 4567
  config.vm.provision :shell, :path => "startup.sh"
end
