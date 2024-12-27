# Ansible Playbook for Setting Up Kubernetes, Docker, Jenkins, SonarQube, and Dependencies

This project contains an **Ansible Playbook** to install and configure the following tools on **Ubuntu EC2 Instances**:
- **Kubernetes** (kubectl, kind)
- **Docker**
- **Jenkins**
- **SonarQube**
- **Java OpenJDK 17**
- **PostgreSQL** (for SonarQube)
- **Other dependencies** such as package updates and permission setups

## Requirements

- **Ansible** version 2.x or higher
- **Ubuntu EC2 Servers** (slave nodes) with SSH access
- **Root privileges** on the target servers
- The following tools should be available:
  - **Git**
  - **Docker**
  - **Kubernetes** (kubectl, kind)
  - **Java OpenJDK 17**
  - **Jenkins**
  - **SonarQube**

## Setup Steps

### 1. Clone the Repository

Clone this repository to your local machine or the server where **Ansible** is installed:

```bash
git clone <repository_link>
cd <repository_folder>
```
### 2. Configure the Inventory File
In the inventory file, define the target EC2 nodes under the [slave] group like this:
```bash
[slave]
your_ec2_instance_ip ansible_ssh_user=ubuntu ansible_private-key=./ivolve.pem
```

### 3. Update Variable Settings
In the ansible/roles/sonarqube/vars/main.yml file, modify the following variables to match your setup:

- postgres_root_user: The root PostgreSQL username
- postgres_root_pass: The root PostgreSQL password
- psql_sonar_username: PostgreSQL username for the SonarQube database
- psql_sonar_password: PostgreSQL password for the SonarQube database
- sonarqube_version: The version of SonarQube you want to install
- sonar_web_port: The port SonarQube will run on (default is 9000)

### 4. Run the Playbook
Run the Ansible playbook to install and configure the systems:

```bash
ansible-playbook -i inventory playbook
```
The playbook will perform the following tasks:

Set up Docker on the target server:
- Install OpenJDK 17 for Java-based applications
- Install and configure Jenkins to run automatically
- Install SonarQube and configure it to use PostgreSQL as the database
- Install Kubernetes CLI tools like kubectl and kind
- Set up dependencies and required files for each service

### 5. Verify the Installation
After running the playbook, verify that the services are running correctly:

Jenkins
 - Open your browser and go to http://<your_ec2_instance_ip>:8080
 - Follow the instructions to complete the Jenkins setup.
SonarQube
- Open your browser and go to http://<your_ec2_instance_ip>:9000
- Default login credentials:
Username: admin
Password: admin
Kubernetes
- To verify kubectl installation, use the following command:
```bash
kubectl version --client
```
To verify kind installation, use the command:
```bash
kubectl version 
```
##  Roles in the Playbook
### git
Installs Git on the target server.
### common
Updates system packages and performs system-level upgrades.
### docker
Installs Docker and its dependencies.
Configures the Docker service to start on boot.
### java
Installs OpenJDK 17 to run Java-based applications.
### jenkins
Installs Jenkins and configures it to run automatically on boot.
### sonarqube
Installs PostgreSQL and configures it to work with SonarQube.
Installs SonarQube and configures it to use PostgreSQL as the database.
Sets up SonarQube as a systemd service to start automatically.

### Kubernetes
Installs Kubernetes CLI tools such as kubectl and kind.

### dependance
Sets up configuration directories for Jenkins.
Changes permissions for the Docker socket file.
Copies the kube configuration for Jenkins.

## Technical Details for Each Role

### common Role
Package Updates: Updates system packages to ensure the system is running with the latest available versions.
Install Essential Packages: Installs tools like curl and wget to ensure the necessary utilities are available.

### docker Role
Install Docker: Installs Docker from the official repository for Ubuntu.
Docker Group Setup: Adds the user to the Docker group to allow running Docker commands without root privileges.

### java Role
Install OpenJDK 17: Installs Java 17, which is required by tools like Jenkins and SonarQube.

### sonarqube Role
Install PostgreSQL: Installs and configures PostgreSQL for SonarQube.
Set Up SonarQube: Downloads and installs SonarQube, then configures it to use PostgreSQL as its database.

### jenkins Role
Install Jenkins: Installs Jenkins and configures it to start automatically on boot.
