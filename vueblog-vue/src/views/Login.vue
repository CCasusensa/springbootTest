<template>
  <div>
    <el-container>
      <el-header>
        <img class="logo" src="https://i.imgur.com/hXKwggL.png" alt="" />
      </el-header>
      <el-main>
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
          <el-form-item label="帳號" prop="username">
            <el-input v-model="ruleForm.username"></el-input>
          </el-form-item>
          <el-form-item label="密碼" prop="password">
            <el-input type = "password" v-model="ruleForm.password"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="submitForm('ruleForm')">登入</el-button>
            <el-button @click="resetForm('ruleForm')">重置</el-button>
          </el-form-item>
        </el-form>
      </el-main>
    </el-container>
  </div>
</template>

<script>
export default {
  name: "Login",
  data() {
    return {
      ruleForm: {
        username: 'markerhub',
        password: '111111'
      },
      rules: {
        username: [
          { required: true, message: '請輸入帳號', trigger: 'blur' },
          { min: 3, max: 15, message: '長度必須 3 到 5 個字符', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '請輸入密碼', trigger: 'blur' },
          { min: 3, max: 15, message: '長度必須 3 到 5 個字符', trigger: 'blur' }
        ]
      }
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          const _this = this;
          this.$axios.post('/login', this.ruleForm).then(response => {
            const jwt = response.headers['authorization'];
            const userInfo = response.data.data;
            // 把數據共享出去
            _this.$store.commit("SET_TOKEN", jwt);
            _this.$store.commit("SET_USERINFO", userInfo);

            // 獲取訊息
            console.log(_this.$store.getters.getUserInfo);

            _this.$router.push("/blog");
          });
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    }
  }
}
</script>

<style>
.el-header, .el-footer {
  background-color: #B3C0D1;
  color: #333;
  text-align: center;
  line-height: 60px;
}

.el-aside {
  background-color: #D3DCE6;
  color: #333;
  text-align: center;
  line-height: 200px;
}

.el-main {
  /*background-color: #E9EEF3;*/
  color: #333;
  text-align: center;
  line-height: 160px;
}

body > .el-container {
  margin-bottom: 40px;
}

.el-container:nth-child(5) .el-aside,
.el-container:nth-child(6) .el-aside {
  line-height: 260px;
}

.el-container:nth-child(7) .el-aside {
  line-height: 320px;
}
.logo {
  height: 80px;
  margin-top: -10px;
  margin-right: -100px
}
.demo-ruleForm {
  max-width: 500px;
  margin: 0 auto;
}
</style>