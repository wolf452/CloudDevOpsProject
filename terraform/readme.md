# Terraform AWS Infrastructure🚀

Terraform is an Infrastructure as Code (IaC) tool that allows you to define and provision infrastructure in a declarative way, ensuring consistency, automation, and easier collaboration.

This project is a Terraform-based setup for deploying a highly customizable AWS infrastructure. Below is a detailed explanation of each component and module used in this configuration.

## Overview📚

This project provisions a Virtual Private Cloud (VPC), subnets, an internet gateway, security groups, EC2 instances, and sets up logging and monitoring using AWS CloudWatch. It also includes an SNS topic for notifications.

## Prerequisites⭐

- Install Terraform.
- Install AWS CLI and configure it with your credentials (`aws configure`).
- Ensure you have sufficient IAM permissions to create resources in AWS.

## Directory Structure🏗️

- `main.tf`: The root configuration file that integrates all modules.
- `variables.tf`: Defines the input variables for the root module.
- `output.tf`: Specifies the output values from the root module.
- `terraform.tfvars`: Contains the actual values for variables.
- `modules/`: Contains reusable Terraform modules.
![structure](https://github.com/user-attachments/assets/02e40b4f-39f6-4d1b-91e5-c95ff7c7d72c)

## Modules Description

### VPC Module (`modules/vpc`)

├── [**VPC**](https://github.com/wolf452/CloudDevOpsProject/tree/main/terraform/modules/vpc):
<br />
**Purpose**: Creates a Virtual Private Cloud (VPC) in AWS.

**Resources**:
- `aws_vpc`: Provisions the VPC with DNS support and hostnames enabled.

**Inputs**:
- `name`: Name tag for the VPC.
- `cidr`: CIDR block for the VPC.

**Outputs**:
- `vpc_id`: The ID of the created VPC.

![VPC](https://github.com/user-attachments/assets/e73a7fa0-1d96-47c8-bd53-ca8e42778047)
![VPC-VAR](https://github.com/user-attachments/assets/0428ebfb-5697-4976-bfbb-f2c15c02ae9a)


### Subnet Module (`modules/subnet`)

├── [**Subnet**](https://github.com/wolf452/CloudDevOpsProject/tree/main/terraform/modules/subnet):
<br />
**Purpose**: Creates public subnets within the VPC.

**Resources**:
- `aws_subnet`: Provisions subnets with specified CIDR blocks and availability zones.

**Inputs**:
- `vpc_id`: ID of the VPC where subnets will be created.
- `subnets`: List of subnet configurations (CIDR, availability zone, and public IP mapping).

**Outputs**:
- `public_subnet_ids`: IDs of the created public subnets.

![SUBNET](https://github.com/user-attachments/assets/f5db557f-9307-49e5-9d7d-8a0535427f2f)
![SUBNET](https://github.com/user-attachments/assets/d9240745-ccf5-43b4-b5be-55f52a1412bb)

### Internet Gateway Module (`modules/internet-gateway&aws_route_table`)

├── [**internet-gateway**](https://github.com/wolf452/CloudDevOpsProject/tree/main/terraform/modules/internet-gateway):
<br />
**Purpose**: Sets up an internet gateway and associates it with the VPC's route table for internet access.

**Resources**:
- `aws_internet_gateway`: Creates an internet gateway.
- `aws_route_table`: Configures a route table with a default route to the internet gateway.
- `aws_route_table_association`: Associates the route table with subnets.

**Inputs**:
- `vpc_id`: ID of the VPC.
- `subnets`: List of subnets to associate with the route table.

**Outputs**:
- `internet_gateway_id`: ID of the created internet gateway.
- `route_table_id`: ID of the associated route table.

![first-igw](https://github.com/user-attachments/assets/13657bbf-a38e-4f65-9d82-d16398bd3f8f)
![second-igw](https://github.com/user-attachments/assets/45b4d91d-1384-4353-9aca-985c747da4d2)
![var-igw](https://github.com/user-attachments/assets/1612a3e9-f470-4d52-8c66-5c4c155bddd4)

### Security Group Module (`modules/security-group`)

├── [**Security Group**](https://github.com/wolf452/CloudDevOpsProject/tree/main/terraform/modules/security-group):
<br />
**Purpose**: Defines security groups and rules for controlling inbound traffic.

**Resources**:
- `aws_security_group`: Creates a security group in the VPC.
- `aws_security_group_rule`: Configures ingress rules based on user input.

**Inputs**:
- `vpc_id`: ID of the VPC.
- `security_group_rules`: List of ingress rules (protocol, port range, and CIDR blocks).

**Outputs**:
- `security_group_id`: ID of the created security group.

![sg](https://github.com/user-attachments/assets/82d2d205-9ae6-4ae7-9671-4d0b5056e06a)
![sg-var](https://github.com/user-attachments/assets/ac9078b1-730f-4585-88bf-c711edbc85f9)


### EC2 Module (`modules/ec2`)
├── [**EC2**](https://github.com/wolf452/CloudDevOpsProject/tree/main/terraform/modules/ec2):
<br />
**Purpose**: Provisions an EC2 instance with CloudWatch monitoring enabled.

**Resources**:
- `aws_instance`: Creates an EC2 instance with the specified AMI, instance type, and key pair.
- `aws_iam_role` and `aws_iam_instance_profile`: Configures an IAM role for the instance to use CloudWatch.

**Inputs**:
- `ami_id`: AMI ID for the instance.
- `instance_type`: EC2 instance type.
- `subnet_id`: Subnet ID for the instance.
- `security_group_id`: Security group ID for the instance.
- `tags`: Tags to assign to the instance.
- `key_name`: Name of the key pair for SSH access.
- `cloudwatch_config_ssm_key`: SSM key for CloudWatch configuration.

**Outputs**:
- `instance_id`: ID of the created EC2 instance.
- `public_ip`: Public IP address of the instance.
- `public_dns`: Public DNS name of the instance.

![ec2-first](https://github.com/user-attachments/assets/8f26f5ae-3e73-41ad-a537-27f0b60e716f)
![ec2-sec](https://github.com/user-attachments/assets/7e9c25cb-d1ff-4b84-a17e-b32e9e08065d)
![ec2-thi](https://github.com/user-attachments/assets/d38162a9-9667-43f2-89ae-b26224e2654e)
![ec2-var-1](https://github.com/user-attachments/assets/555ec2b7-9355-442c-ac3d-78ca2dcd089e)
![ec2-var-2](https://github.com/user-attachments/assets/76221cf2-bf43-4dfd-94b6-13a65e45781d)

### CloudWatch Module (`modules/cloudwatch`)

├── [**CloudWatch**](https://github.com/wolf452/CloudDevOpsProject/tree/main/terraform/modules/cloudwatch):
<br />
**Purpose**: Sets up CloudWatch log group and log stream for monitoring.

**Resources**:
- `aws_cloudwatch_log_group`: Creates a CloudWatch log group.
- `aws_cloudwatch_log_stream`: Creates a log stream within the log group.
- `aws_ssm_parameter`: Stores CloudWatch agent configuration in SSM Parameter Store.

**Inputs**:
- `log_group_name`: Name of the log group.
- `log_stream_name`: Name of the log stream.
- `cloudwatch_config_ssm_key`: SSM key for the CloudWatch agent configuration.

![cloud-watch-1](https://github.com/user-attachments/assets/f67c96a5-f783-4611-925d-1ed3ed02fcd0)
![cloud-watch-2](https://github.com/user-attachments/assets/23c58de0-e00f-4af2-bf23-7b2b31625d5f)
![cloud-watch-var](https://github.com/user-attachments/assets/e5abd241-1b55-4eca-9252-51d19f0ebfc3)

### SNS Module (`modules/sns`)

├── [**SNS**](https://github.com/wolf452/CloudDevOpsProject/tree/main/terraform/modules/sns):
<br />
**Purpose**: Sets up an SNS topic for notifications.

**Resources**:
- `aws_sns_topic`: Creates an SNS topic.
- `aws_sns_topic_subscription`: Subscribes an endpoint (e.g., email) to the topic.

**Inputs**:
- `sns_topic_name`: Name of the SNS topic.
- `subscription_protocol`: Protocol for the subscription (e.g., email).
- `subscription_endpoint`: Endpoint for the subscription (e.g., email address).

**Outputs**:
- `sns_topic_arn`: ARN of the created SNS topic.

![sns-a](https://github.com/user-attachments/assets/d97ab675-12d0-4bba-9960-b3d2aca51560)
![sns-b](https://github.com/user-attachments/assets/ca35c78e-6818-4465-b907-28e4b790bf76)
![sns-c](https://github.com/user-attachments/assets/701c272a-6a6f-4fa4-aec6-62f189276afc)


# Main.tf

## File Contents
### 1. AWS Provider  
## Usage

1. Clone this repository.
2. Modify `terraform.tfvars` with your specific configurations.
3. Initialize the Terraform project:

   ```bash
   terraform init
   ```

4. Validate the configuration:

   ```bash
   terraform validate
   ```

5. Apply the changes:

   ```bash
   terraform apply
   ```

6. Destroy resources when no longer needed:

   ```bash
   terraform destroy
   ```

## Notes

- Ensure your AWS credentials are configured correctly before running Terraform commands.
- Monitor resources in the AWS Management Console to verify successful deployment.
