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

## Modules Description

### VPC Module (`modules/vpc`)
├── [**vpc**](https://github.com/wolf452/CloudDevOpsProject/tree/main/terraform/modules/vpc)
**Purpose**: Creates a Virtual Private Cloud (VPC) in AWS.

**Resources**:
- `aws_vpc`: Provisions the VPC with DNS support and hostnames enabled.

**Inputs**:
- `name`: Name tag for the VPC.
- `cidr`: CIDR block for the VPC.

**Outputs**:
- `vpc_id`: The ID of the created VPC.

### Subnet Module (`modules/subnet`)

**Purpose**: Creates public subnets within the VPC.

**Resources**:
- `aws_subnet`: Provisions subnets with specified CIDR blocks and availability zones.

**Inputs**:
- `vpc_id`: ID of the VPC where subnets will be created.
- `subnets`: List of subnet configurations (CIDR, availability zone, and public IP mapping).

**Outputs**:
- `public_subnet_ids`: IDs of the created public subnets.

### Internet Gateway Module (`modules/internet-gateway`)

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

### Security Group Module (`modules/security-group`)

**Purpose**: Defines security groups and rules for controlling inbound traffic.

**Resources**:
- `aws_security_group`: Creates a security group in the VPC.
- `aws_security_group_rule`: Configures ingress rules based on user input.

**Inputs**:
- `vpc_id`: ID of the VPC.
- `security_group_rules`: List of ingress rules (protocol, port range, and CIDR blocks).

**Outputs**:
- `security_group_id`: ID of the created security group.

### EC2 Module (`modules/ec2`)

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

### CloudWatch Module (`modules/cloudwatch`)

**Purpose**: Sets up CloudWatch log group and log stream for monitoring.

**Resources**:
- `aws_cloudwatch_log_group`: Creates a CloudWatch log group.
- `aws_cloudwatch_log_stream`: Creates a log stream within the log group.
- `aws_ssm_parameter`: Stores CloudWatch agent configuration in SSM Parameter Store.

**Inputs**:
- `log_group_name`: Name of the log group.
- `log_stream_name`: Name of the log stream.
- `cloudwatch_config_ssm_key`: SSM key for the CloudWatch agent configuration.

### SNS Module (`modules/sns`)

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
