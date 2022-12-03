## Installing Docker

<https://medium.com/@Grigorkh/how-to-install-docker-on-ubuntu-19-04-7ccfeda5935>

```
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -
sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable edge"
sudo apt-get update
```

Make sure you are about to install from the Docker repo instead of the default Ubuntu repo:

```
apt-cache policy docker-ce
sudo apt-get install -y docker-ce
sudo systemctl status docker
sudo apt-get install -y docker-ce --fix-missing

```

Add user to docker group:

```
sudo usermod -aG docker ${USER}
```