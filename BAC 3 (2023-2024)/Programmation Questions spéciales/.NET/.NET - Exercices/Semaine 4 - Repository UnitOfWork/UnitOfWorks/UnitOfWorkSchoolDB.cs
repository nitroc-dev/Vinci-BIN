﻿using School.Repository;
using SchoolApp.Models;

namespace School.UnitOfWork
{
    class UnitOfWorkSchoolDB : IUnitOfWorkSchool
    {
        private readonly SchoolContext _context;

        private StudentRepositorySQLServer _studentRepository;

        private BaseRepositorySQL<Section> _sectionsRepository;


        public UnitOfWorkSchoolDB(SchoolContext context)
        {
            this._context = context;
            this._studentRepository = new StudentRepositorySQLServer(context);
            this._sectionsRepository = new BaseRepositorySQL<Section>(context);

        }

        public IStudentRepository StudentsRepository
        {
            get { return this._studentRepository; }
        }

        public IRepository<Section> SectionsRepository
        {
            get { return this._sectionsRepository; }
        }
    }
}
