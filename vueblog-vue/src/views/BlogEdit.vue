<template>
  <div>
    <Header></Header>
    <div class="m-content">
      <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label="標題" prop="title">
          <el-input v-model="ruleForm.title"></el-input>
        </el-form-item>
        <el-form-item label="文章摘要" prop="description">
          <el-input type="textarea" v-model="ruleForm.description"></el-input>
        </el-form-item>
        <el-form-item label="文章內容" prop="content">
          <mavon-editor v-model="ruleForm.content"></mavon-editor>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm('ruleForm')">發表文章</el-button>
          <el-button @click="resetForm('ruleForm')">重置內容</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import Header from "../components/Header";
export default {
  name: "BlogEdit.vue",
  components: {Header},
  data() {
    return {
      ruleForm: {
        id: '',
        title: '',
        description: '',
        content: ''
      },
      rules: {
        title: [
          { required: true, message: '請輸入文章標題', trigger: 'blur' },
          { min: 3, max: 25, message: '文字長度必須在 3 到 25 個字元', trigger: 'blur' }
        ],
        description: [
          { required: true, message: '請輸入文章摘要', trigger: 'change' }
        ],
        content: [
          { trequired: true, message: '請輸入文章內容', trigger: 'blur' }
        ]
      }
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          const _this = this
          this.$axios.post('/blog/edit', this.ruleForm, {
            headers: {
              "Authorization": localStorage.getItem("token")
            }
          }).then(res => {
            console.log(res);
            _this.$alert('操作成功', '提示', {
              confirmButtonText: '確定',
              callback: action => {
                _this.$router.push("/blog")
              }
            });
          });
        } else {
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    }
  },
  created() {
    const blogId = this.$route.params.blogId;
    const _this = this;
    if (blogId) {
      this.$axios.get('/blog/' + blogId).then(res => {
        const blog = res.data.data;
        _this.ruleForm.id = blog.id;
        _this.ruleForm.title = blog.title;
        _this.ruleForm.description = blog.description;
        _this.ruleForm.content = blog.content;
      });
    }
  }
}
</script>

<style scoped>
.m-content {
  text-align: center;
}
</style>