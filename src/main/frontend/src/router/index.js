import { createWebHistory, createRouter } from "vue-router";
import LandingPage from "../views/LandingPage";
import BlogPage from "@/views/BlogPage";
import Contact from "@/views/Contact";
import About from "@/views/About";
import PostPage from "@/views/PostPage";
import LoginPage from "@/views/LoginPage";
import ProfilePage from "@/views/ProfilePage";
import ArticleForm from "../views/ArticleForm";
import AdminPage from "../views/AdminPage";

const routes = [
    {
        name: 'landingPage',
        path: '/',
        component: LandingPage,
        props: true
    },
    {
        name: 'blogPage',
        path: '/blog',
        component: BlogPage,
        props: true
    },
    {
        name: 'about',
        path: '/about',
        component: About,
        props: true
    },
    {
        name: 'post',
        path: '/post:id',
        component: PostPage,
        props: true
    },
    {
        name: 'login',
        path: '/login',
        component: LoginPage,
        props: true
    },
    {
        name: 'profile',
        path: '/profile',
        component: ProfilePage,
        props: true
    },
    {
        name: 'addArticle',
        path: '/addArticle',
        component: ArticleForm,
        props: true
    },
    {
        name: 'admin',
        path: '/admin',
        component: AdminPage,
        props: true
    },
    {
        name: 'contact',
        path: '/contact',
        component: Contact,
        props: true
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes,
})

export default router;